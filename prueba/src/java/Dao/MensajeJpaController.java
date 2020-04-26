/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Dao.exceptions.NonexistentEntityException;
import Dto.Mensaje;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Dto.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Pc-Victor
 */
public class MensajeJpaController implements Serializable {

    public MensajeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Mensaje mensaje) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario envia = mensaje.getEnvia();
            if (envia != null) {
                envia = em.getReference(envia.getClass(), envia.getId());
                mensaje.setEnvia(envia);
            }
            Usuario recibe = mensaje.getRecibe();
            if (recibe != null) {
                recibe = em.getReference(recibe.getClass(), recibe.getId());
                mensaje.setRecibe(recibe);
            }
            em.persist(mensaje);
            if (envia != null) {
                envia.getMensajeList().add(mensaje);
                envia = em.merge(envia);
            }
            if (recibe != null) {
                recibe.getMensajeList().add(mensaje);
                recibe = em.merge(recibe);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Mensaje mensaje) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Mensaje persistentMensaje = em.find(Mensaje.class, mensaje.getId());
            Usuario enviaOld = persistentMensaje.getEnvia();
            Usuario enviaNew = mensaje.getEnvia();
            Usuario recibeOld = persistentMensaje.getRecibe();
            Usuario recibeNew = mensaje.getRecibe();
            if (enviaNew != null) {
                enviaNew = em.getReference(enviaNew.getClass(), enviaNew.getId());
                mensaje.setEnvia(enviaNew);
            }
            if (recibeNew != null) {
                recibeNew = em.getReference(recibeNew.getClass(), recibeNew.getId());
                mensaje.setRecibe(recibeNew);
            }
            mensaje = em.merge(mensaje);
            if (enviaOld != null && !enviaOld.equals(enviaNew)) {
                enviaOld.getMensajeList().remove(mensaje);
                enviaOld = em.merge(enviaOld);
            }
            if (enviaNew != null && !enviaNew.equals(enviaOld)) {
                enviaNew.getMensajeList().add(mensaje);
                enviaNew = em.merge(enviaNew);
            }
            if (recibeOld != null && !recibeOld.equals(recibeNew)) {
                recibeOld.getMensajeList().remove(mensaje);
                recibeOld = em.merge(recibeOld);
            }
            if (recibeNew != null && !recibeNew.equals(recibeOld)) {
                recibeNew.getMensajeList().add(mensaje);
                recibeNew = em.merge(recibeNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = mensaje.getId();
                if (findMensaje(id) == null) {
                    throw new NonexistentEntityException("The mensaje with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Mensaje mensaje;
            try {
                mensaje = em.getReference(Mensaje.class, id);
                mensaje.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The mensaje with id " + id + " no longer exists.", enfe);
            }
            Usuario envia = mensaje.getEnvia();
            if (envia != null) {
                envia.getMensajeList().remove(mensaje);
                envia = em.merge(envia);
            }
            Usuario recibe = mensaje.getRecibe();
            if (recibe != null) {
                recibe.getMensajeList().remove(mensaje);
                recibe = em.merge(recibe);
            }
            em.remove(mensaje);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Mensaje> findMensajeEntities() {
        return findMensajeEntities(true, -1, -1);
    }

    public List<Mensaje> findMensajeEntities(int maxResults, int firstResult) {
        return findMensajeEntities(false, maxResults, firstResult);
    }

    private List<Mensaje> findMensajeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Mensaje.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Mensaje findMensaje(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Mensaje.class, id);
        } finally {
            em.close();
        }
    }

    public int getMensajeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Mensaje> rt = cq.from(Mensaje.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
