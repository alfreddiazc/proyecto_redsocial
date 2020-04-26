/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Dto.Usuario;
import Dto.Etiqueta;
import java.util.ArrayList;
import java.util.List;
import Dto.Comentario;
import Dto.Publicacion;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Pc-Victor
 */
public class PublicacionJpaController implements Serializable {

    public PublicacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Publicacion publicacion) {
        if (publicacion.getEtiquetaList() == null) {
            publicacion.setEtiquetaList(new ArrayList<Etiqueta>());
        }
        if (publicacion.getComentarioList() == null) {
            publicacion.setComentarioList(new ArrayList<Comentario>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuario = publicacion.getUsuario();
            if (usuario != null) {
                usuario = em.getReference(usuario.getClass(), usuario.getId());
                publicacion.setUsuario(usuario);
            }
            List<Etiqueta> attachedEtiquetaList = new ArrayList<Etiqueta>();
            for (Etiqueta etiquetaListEtiquetaToAttach : publicacion.getEtiquetaList()) {
                etiquetaListEtiquetaToAttach = em.getReference(etiquetaListEtiquetaToAttach.getClass(), etiquetaListEtiquetaToAttach.getId());
                attachedEtiquetaList.add(etiquetaListEtiquetaToAttach);
            }
            publicacion.setEtiquetaList(attachedEtiquetaList);
            List<Comentario> attachedComentarioList = new ArrayList<Comentario>();
            for (Comentario comentarioListComentarioToAttach : publicacion.getComentarioList()) {
                comentarioListComentarioToAttach = em.getReference(comentarioListComentarioToAttach.getClass(), comentarioListComentarioToAttach.getId());
                attachedComentarioList.add(comentarioListComentarioToAttach);
            }
            publicacion.setComentarioList(attachedComentarioList);
            em.persist(publicacion);
            if (usuario != null) {
                usuario.getPublicacionList().add(publicacion);
                usuario = em.merge(usuario);
            }
            for (Etiqueta etiquetaListEtiqueta : publicacion.getEtiquetaList()) {
                Publicacion oldPublicacionOfEtiquetaListEtiqueta = etiquetaListEtiqueta.getPublicacion();
                etiquetaListEtiqueta.setPublicacion(publicacion);
                etiquetaListEtiqueta = em.merge(etiquetaListEtiqueta);
                if (oldPublicacionOfEtiquetaListEtiqueta != null) {
                    oldPublicacionOfEtiquetaListEtiqueta.getEtiquetaList().remove(etiquetaListEtiqueta);
                    oldPublicacionOfEtiquetaListEtiqueta = em.merge(oldPublicacionOfEtiquetaListEtiqueta);
                }
            }
            for (Comentario comentarioListComentario : publicacion.getComentarioList()) {
                Publicacion oldPublicacionOfComentarioListComentario = comentarioListComentario.getPublicacion();
                comentarioListComentario.setPublicacion(publicacion);
                comentarioListComentario = em.merge(comentarioListComentario);
                if (oldPublicacionOfComentarioListComentario != null) {
                    oldPublicacionOfComentarioListComentario.getComentarioList().remove(comentarioListComentario);
                    oldPublicacionOfComentarioListComentario = em.merge(oldPublicacionOfComentarioListComentario);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Publicacion publicacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Publicacion persistentPublicacion = em.find(Publicacion.class, publicacion.getId());
            Usuario usuarioOld = persistentPublicacion.getUsuario();
            Usuario usuarioNew = publicacion.getUsuario();
            List<Etiqueta> etiquetaListOld = persistentPublicacion.getEtiquetaList();
            List<Etiqueta> etiquetaListNew = publicacion.getEtiquetaList();
            List<Comentario> comentarioListOld = persistentPublicacion.getComentarioList();
            List<Comentario> comentarioListNew = publicacion.getComentarioList();
            if (usuarioNew != null) {
                usuarioNew = em.getReference(usuarioNew.getClass(), usuarioNew.getId());
                publicacion.setUsuario(usuarioNew);
            }
            List<Etiqueta> attachedEtiquetaListNew = new ArrayList<Etiqueta>();
            for (Etiqueta etiquetaListNewEtiquetaToAttach : etiquetaListNew) {
                etiquetaListNewEtiquetaToAttach = em.getReference(etiquetaListNewEtiquetaToAttach.getClass(), etiquetaListNewEtiquetaToAttach.getId());
                attachedEtiquetaListNew.add(etiquetaListNewEtiquetaToAttach);
            }
            etiquetaListNew = attachedEtiquetaListNew;
            publicacion.setEtiquetaList(etiquetaListNew);
            List<Comentario> attachedComentarioListNew = new ArrayList<Comentario>();
            for (Comentario comentarioListNewComentarioToAttach : comentarioListNew) {
                comentarioListNewComentarioToAttach = em.getReference(comentarioListNewComentarioToAttach.getClass(), comentarioListNewComentarioToAttach.getId());
                attachedComentarioListNew.add(comentarioListNewComentarioToAttach);
            }
            comentarioListNew = attachedComentarioListNew;
            publicacion.setComentarioList(comentarioListNew);
            publicacion = em.merge(publicacion);
            if (usuarioOld != null && !usuarioOld.equals(usuarioNew)) {
                usuarioOld.getPublicacionList().remove(publicacion);
                usuarioOld = em.merge(usuarioOld);
            }
            if (usuarioNew != null && !usuarioNew.equals(usuarioOld)) {
                usuarioNew.getPublicacionList().add(publicacion);
                usuarioNew = em.merge(usuarioNew);
            }
            for (Etiqueta etiquetaListOldEtiqueta : etiquetaListOld) {
                if (!etiquetaListNew.contains(etiquetaListOldEtiqueta)) {
                    etiquetaListOldEtiqueta.setPublicacion(null);
                    etiquetaListOldEtiqueta = em.merge(etiquetaListOldEtiqueta);
                }
            }
            for (Etiqueta etiquetaListNewEtiqueta : etiquetaListNew) {
                if (!etiquetaListOld.contains(etiquetaListNewEtiqueta)) {
                    Publicacion oldPublicacionOfEtiquetaListNewEtiqueta = etiquetaListNewEtiqueta.getPublicacion();
                    etiquetaListNewEtiqueta.setPublicacion(publicacion);
                    etiquetaListNewEtiqueta = em.merge(etiquetaListNewEtiqueta);
                    if (oldPublicacionOfEtiquetaListNewEtiqueta != null && !oldPublicacionOfEtiquetaListNewEtiqueta.equals(publicacion)) {
                        oldPublicacionOfEtiquetaListNewEtiqueta.getEtiquetaList().remove(etiquetaListNewEtiqueta);
                        oldPublicacionOfEtiquetaListNewEtiqueta = em.merge(oldPublicacionOfEtiquetaListNewEtiqueta);
                    }
                }
            }
            for (Comentario comentarioListOldComentario : comentarioListOld) {
                if (!comentarioListNew.contains(comentarioListOldComentario)) {
                    comentarioListOldComentario.setPublicacion(null);
                    comentarioListOldComentario = em.merge(comentarioListOldComentario);
                }
            }
            for (Comentario comentarioListNewComentario : comentarioListNew) {
                if (!comentarioListOld.contains(comentarioListNewComentario)) {
                    Publicacion oldPublicacionOfComentarioListNewComentario = comentarioListNewComentario.getPublicacion();
                    comentarioListNewComentario.setPublicacion(publicacion);
                    comentarioListNewComentario = em.merge(comentarioListNewComentario);
                    if (oldPublicacionOfComentarioListNewComentario != null && !oldPublicacionOfComentarioListNewComentario.equals(publicacion)) {
                        oldPublicacionOfComentarioListNewComentario.getComentarioList().remove(comentarioListNewComentario);
                        oldPublicacionOfComentarioListNewComentario = em.merge(oldPublicacionOfComentarioListNewComentario);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = publicacion.getId();
                if (findPublicacion(id) == null) {
                    throw new NonexistentEntityException("The publicacion with id " + id + " no longer exists.");
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
            Publicacion publicacion;
            try {
                publicacion = em.getReference(Publicacion.class, id);
                publicacion.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The publicacion with id " + id + " no longer exists.", enfe);
            }
            Usuario usuario = publicacion.getUsuario();
            if (usuario != null) {
                usuario.getPublicacionList().remove(publicacion);
                usuario = em.merge(usuario);
            }
            List<Etiqueta> etiquetaList = publicacion.getEtiquetaList();
            for (Etiqueta etiquetaListEtiqueta : etiquetaList) {
                etiquetaListEtiqueta.setPublicacion(null);
                etiquetaListEtiqueta = em.merge(etiquetaListEtiqueta);
            }
            List<Comentario> comentarioList = publicacion.getComentarioList();
            for (Comentario comentarioListComentario : comentarioList) {
                comentarioListComentario.setPublicacion(null);
                comentarioListComentario = em.merge(comentarioListComentario);
            }
            em.remove(publicacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Publicacion> findPublicacionEntities() {
        return findPublicacionEntities(true, -1, -1);
    }

    public List<Publicacion> findPublicacionEntities(int maxResults, int firstResult) {
        return findPublicacionEntities(false, maxResults, firstResult);
    }

    private List<Publicacion> findPublicacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Publicacion.class));
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

    public Publicacion findPublicacion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Publicacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getPublicacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Publicacion> rt = cq.from(Publicacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
