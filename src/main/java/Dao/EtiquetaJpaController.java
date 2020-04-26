/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Dao.exceptions.NonexistentEntityException;
import Dto.Etiqueta;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Dto.Publicacion;
import Dto.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author USUARIO
 */
public class EtiquetaJpaController implements Serializable {

    public EtiquetaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Etiqueta etiqueta) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Publicacion publicacion = etiqueta.getPublicacion();
            if (publicacion != null) {
                publicacion = em.getReference(publicacion.getClass(), publicacion.getId());
                etiqueta.setPublicacion(publicacion);
            }
            Usuario usuario = etiqueta.getUsuario();
            if (usuario != null) {
                usuario = em.getReference(usuario.getClass(), usuario.getId());
                etiqueta.setUsuario(usuario);
            }
            em.persist(etiqueta);
            if (publicacion != null) {
                publicacion.getEtiquetaList().add(etiqueta);
                publicacion = em.merge(publicacion);
            }
            if (usuario != null) {
                usuario.getEtiquetaList().add(etiqueta);
                usuario = em.merge(usuario);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Etiqueta etiqueta) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Etiqueta persistentEtiqueta = em.find(Etiqueta.class, etiqueta.getId());
            Publicacion publicacionOld = persistentEtiqueta.getPublicacion();
            Publicacion publicacionNew = etiqueta.getPublicacion();
            Usuario usuarioOld = persistentEtiqueta.getUsuario();
            Usuario usuarioNew = etiqueta.getUsuario();
            if (publicacionNew != null) {
                publicacionNew = em.getReference(publicacionNew.getClass(), publicacionNew.getId());
                etiqueta.setPublicacion(publicacionNew);
            }
            if (usuarioNew != null) {
                usuarioNew = em.getReference(usuarioNew.getClass(), usuarioNew.getId());
                etiqueta.setUsuario(usuarioNew);
            }
            etiqueta = em.merge(etiqueta);
            if (publicacionOld != null && !publicacionOld.equals(publicacionNew)) {
                publicacionOld.getEtiquetaList().remove(etiqueta);
                publicacionOld = em.merge(publicacionOld);
            }
            if (publicacionNew != null && !publicacionNew.equals(publicacionOld)) {
                publicacionNew.getEtiquetaList().add(etiqueta);
                publicacionNew = em.merge(publicacionNew);
            }
            if (usuarioOld != null && !usuarioOld.equals(usuarioNew)) {
                usuarioOld.getEtiquetaList().remove(etiqueta);
                usuarioOld = em.merge(usuarioOld);
            }
            if (usuarioNew != null && !usuarioNew.equals(usuarioOld)) {
                usuarioNew.getEtiquetaList().add(etiqueta);
                usuarioNew = em.merge(usuarioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = etiqueta.getId();
                if (findEtiqueta(id) == null) {
                    throw new NonexistentEntityException("The etiqueta with id " + id + " no longer exists.");
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
            Etiqueta etiqueta;
            try {
                etiqueta = em.getReference(Etiqueta.class, id);
                etiqueta.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The etiqueta with id " + id + " no longer exists.", enfe);
            }
            Publicacion publicacion = etiqueta.getPublicacion();
            if (publicacion != null) {
                publicacion.getEtiquetaList().remove(etiqueta);
                publicacion = em.merge(publicacion);
            }
            Usuario usuario = etiqueta.getUsuario();
            if (usuario != null) {
                usuario.getEtiquetaList().remove(etiqueta);
                usuario = em.merge(usuario);
            }
            em.remove(etiqueta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Etiqueta> findEtiquetaEntities() {
        return findEtiquetaEntities(true, -1, -1);
    }

    public List<Etiqueta> findEtiquetaEntities(int maxResults, int firstResult) {
        return findEtiquetaEntities(false, maxResults, firstResult);
    }

    private List<Etiqueta> findEtiquetaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Etiqueta.class));
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

    public Etiqueta findEtiqueta(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Etiqueta.class, id);
        } finally {
            em.close();
        }
    }

    public int getEtiquetaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Etiqueta> rt = cq.from(Etiqueta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
