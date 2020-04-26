/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Dao.exceptions.IllegalOrphanException;
import Dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Dto.Etiqueta;
import java.util.ArrayList;
import java.util.List;
import Dto.Amistad;
import Dto.Mensaje;
import Dto.Comentario;
import Dto.Publicacion;
import Dto.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Pc-Victor
 */
public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) {
        if (usuario.getEtiquetaList() == null) {
            usuario.setEtiquetaList(new ArrayList<Etiqueta>());
        }
        if (usuario.getAmistadList() == null) {
            usuario.setAmistadList(new ArrayList<Amistad>());
        }
        if (usuario.getAmistadList1() == null) {
            usuario.setAmistadList1(new ArrayList<Amistad>());
        }
        if (usuario.getMensajeList() == null) {
            usuario.setMensajeList(new ArrayList<Mensaje>());
        }
        if (usuario.getMensajeList1() == null) {
            usuario.setMensajeList1(new ArrayList<Mensaje>());
        }
        if (usuario.getComentarioList() == null) {
            usuario.setComentarioList(new ArrayList<Comentario>());
        }
        if (usuario.getPublicacionList() == null) {
            usuario.setPublicacionList(new ArrayList<Publicacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Etiqueta> attachedEtiquetaList = new ArrayList<Etiqueta>();
            for (Etiqueta etiquetaListEtiquetaToAttach : usuario.getEtiquetaList()) {
                etiquetaListEtiquetaToAttach = em.getReference(etiquetaListEtiquetaToAttach.getClass(), etiquetaListEtiquetaToAttach.getId());
                attachedEtiquetaList.add(etiquetaListEtiquetaToAttach);
            }
            usuario.setEtiquetaList(attachedEtiquetaList);
            List<Amistad> attachedAmistadList = new ArrayList<Amistad>();
            for (Amistad amistadListAmistadToAttach : usuario.getAmistadList()) {
                amistadListAmistadToAttach = em.getReference(amistadListAmistadToAttach.getClass(), amistadListAmistadToAttach.getId());
                attachedAmistadList.add(amistadListAmistadToAttach);
            }
            usuario.setAmistadList(attachedAmistadList);
            List<Amistad> attachedAmistadList1 = new ArrayList<Amistad>();
            for (Amistad amistadList1AmistadToAttach : usuario.getAmistadList1()) {
                amistadList1AmistadToAttach = em.getReference(amistadList1AmistadToAttach.getClass(), amistadList1AmistadToAttach.getId());
                attachedAmistadList1.add(amistadList1AmistadToAttach);
            }
            usuario.setAmistadList1(attachedAmistadList1);
            List<Mensaje> attachedMensajeList = new ArrayList<Mensaje>();
            for (Mensaje mensajeListMensajeToAttach : usuario.getMensajeList()) {
                mensajeListMensajeToAttach = em.getReference(mensajeListMensajeToAttach.getClass(), mensajeListMensajeToAttach.getId());
                attachedMensajeList.add(mensajeListMensajeToAttach);
            }
            usuario.setMensajeList(attachedMensajeList);
            List<Mensaje> attachedMensajeList1 = new ArrayList<Mensaje>();
            for (Mensaje mensajeList1MensajeToAttach : usuario.getMensajeList1()) {
                mensajeList1MensajeToAttach = em.getReference(mensajeList1MensajeToAttach.getClass(), mensajeList1MensajeToAttach.getId());
                attachedMensajeList1.add(mensajeList1MensajeToAttach);
            }
            usuario.setMensajeList1(attachedMensajeList1);
            List<Comentario> attachedComentarioList = new ArrayList<Comentario>();
            for (Comentario comentarioListComentarioToAttach : usuario.getComentarioList()) {
                comentarioListComentarioToAttach = em.getReference(comentarioListComentarioToAttach.getClass(), comentarioListComentarioToAttach.getId());
                attachedComentarioList.add(comentarioListComentarioToAttach);
            }
            usuario.setComentarioList(attachedComentarioList);
            List<Publicacion> attachedPublicacionList = new ArrayList<Publicacion>();
            for (Publicacion publicacionListPublicacionToAttach : usuario.getPublicacionList()) {
                publicacionListPublicacionToAttach = em.getReference(publicacionListPublicacionToAttach.getClass(), publicacionListPublicacionToAttach.getId());
                attachedPublicacionList.add(publicacionListPublicacionToAttach);
            }
            usuario.setPublicacionList(attachedPublicacionList);
            em.persist(usuario);
            for (Etiqueta etiquetaListEtiqueta : usuario.getEtiquetaList()) {
                Usuario oldUsuarioOfEtiquetaListEtiqueta = etiquetaListEtiqueta.getUsuario();
                etiquetaListEtiqueta.setUsuario(usuario);
                etiquetaListEtiqueta = em.merge(etiquetaListEtiqueta);
                if (oldUsuarioOfEtiquetaListEtiqueta != null) {
                    oldUsuarioOfEtiquetaListEtiqueta.getEtiquetaList().remove(etiquetaListEtiqueta);
                    oldUsuarioOfEtiquetaListEtiqueta = em.merge(oldUsuarioOfEtiquetaListEtiqueta);
                }
            }
            for (Amistad amistadListAmistad : usuario.getAmistadList()) {
                Usuario oldUsuarioOfAmistadListAmistad = amistadListAmistad.getUsuario();
                amistadListAmistad.setUsuario(usuario);
                amistadListAmistad = em.merge(amistadListAmistad);
                if (oldUsuarioOfAmistadListAmistad != null) {
                    oldUsuarioOfAmistadListAmistad.getAmistadList().remove(amistadListAmistad);
                    oldUsuarioOfAmistadListAmistad = em.merge(oldUsuarioOfAmistadListAmistad);
                }
            }
            for (Amistad amistadList1Amistad : usuario.getAmistadList1()) {
                Usuario oldAmistadOfAmistadList1Amistad = amistadList1Amistad.getAmistad();
                amistadList1Amistad.setAmistad(usuario);
                amistadList1Amistad = em.merge(amistadList1Amistad);
                if (oldAmistadOfAmistadList1Amistad != null) {
                    oldAmistadOfAmistadList1Amistad.getAmistadList1().remove(amistadList1Amistad);
                    oldAmistadOfAmistadList1Amistad = em.merge(oldAmistadOfAmistadList1Amistad);
                }
            }
            for (Mensaje mensajeListMensaje : usuario.getMensajeList()) {
                Usuario oldEnviaOfMensajeListMensaje = mensajeListMensaje.getEnvia();
                mensajeListMensaje.setEnvia(usuario);
                mensajeListMensaje = em.merge(mensajeListMensaje);
                if (oldEnviaOfMensajeListMensaje != null) {
                    oldEnviaOfMensajeListMensaje.getMensajeList().remove(mensajeListMensaje);
                    oldEnviaOfMensajeListMensaje = em.merge(oldEnviaOfMensajeListMensaje);
                }
            }
            for (Mensaje mensajeList1Mensaje : usuario.getMensajeList1()) {
                Usuario oldRecibeOfMensajeList1Mensaje = mensajeList1Mensaje.getRecibe();
                mensajeList1Mensaje.setRecibe(usuario);
                mensajeList1Mensaje = em.merge(mensajeList1Mensaje);
                if (oldRecibeOfMensajeList1Mensaje != null) {
                    oldRecibeOfMensajeList1Mensaje.getMensajeList1().remove(mensajeList1Mensaje);
                    oldRecibeOfMensajeList1Mensaje = em.merge(oldRecibeOfMensajeList1Mensaje);
                }
            }
            for (Comentario comentarioListComentario : usuario.getComentarioList()) {
                Usuario oldUsuarioOfComentarioListComentario = comentarioListComentario.getUsuario();
                comentarioListComentario.setUsuario(usuario);
                comentarioListComentario = em.merge(comentarioListComentario);
                if (oldUsuarioOfComentarioListComentario != null) {
                    oldUsuarioOfComentarioListComentario.getComentarioList().remove(comentarioListComentario);
                    oldUsuarioOfComentarioListComentario = em.merge(oldUsuarioOfComentarioListComentario);
                }
            }
            for (Publicacion publicacionListPublicacion : usuario.getPublicacionList()) {
                Usuario oldUsuarioOfPublicacionListPublicacion = publicacionListPublicacion.getUsuario();
                publicacionListPublicacion.setUsuario(usuario);
                publicacionListPublicacion = em.merge(publicacionListPublicacion);
                if (oldUsuarioOfPublicacionListPublicacion != null) {
                    oldUsuarioOfPublicacionListPublicacion.getPublicacionList().remove(publicacionListPublicacion);
                    oldUsuarioOfPublicacionListPublicacion = em.merge(oldUsuarioOfPublicacionListPublicacion);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getId());
            List<Etiqueta> etiquetaListOld = persistentUsuario.getEtiquetaList();
            List<Etiqueta> etiquetaListNew = usuario.getEtiquetaList();
            List<Amistad> amistadListOld = persistentUsuario.getAmistadList();
            List<Amistad> amistadListNew = usuario.getAmistadList();
            List<Amistad> amistadList1Old = persistentUsuario.getAmistadList1();
            List<Amistad> amistadList1New = usuario.getAmistadList1();
            List<Mensaje> mensajeListOld = persistentUsuario.getMensajeList();
            List<Mensaje> mensajeListNew = usuario.getMensajeList();
            List<Mensaje> mensajeList1Old = persistentUsuario.getMensajeList1();
            List<Mensaje> mensajeList1New = usuario.getMensajeList1();
            List<Comentario> comentarioListOld = persistentUsuario.getComentarioList();
            List<Comentario> comentarioListNew = usuario.getComentarioList();
            List<Publicacion> publicacionListOld = persistentUsuario.getPublicacionList();
            List<Publicacion> publicacionListNew = usuario.getPublicacionList();
            List<String> illegalOrphanMessages = null;
            for (Mensaje mensajeListOldMensaje : mensajeListOld) {
                if (!mensajeListNew.contains(mensajeListOldMensaje)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Mensaje " + mensajeListOldMensaje + " since its envia field is not nullable.");
                }
            }
            for (Mensaje mensajeList1OldMensaje : mensajeList1Old) {
                if (!mensajeList1New.contains(mensajeList1OldMensaje)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Mensaje " + mensajeList1OldMensaje + " since its recibe field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Etiqueta> attachedEtiquetaListNew = new ArrayList<Etiqueta>();
            for (Etiqueta etiquetaListNewEtiquetaToAttach : etiquetaListNew) {
                etiquetaListNewEtiquetaToAttach = em.getReference(etiquetaListNewEtiquetaToAttach.getClass(), etiquetaListNewEtiquetaToAttach.getId());
                attachedEtiquetaListNew.add(etiquetaListNewEtiquetaToAttach);
            }
            etiquetaListNew = attachedEtiquetaListNew;
            usuario.setEtiquetaList(etiquetaListNew);
            List<Amistad> attachedAmistadListNew = new ArrayList<Amistad>();
            for (Amistad amistadListNewAmistadToAttach : amistadListNew) {
                amistadListNewAmistadToAttach = em.getReference(amistadListNewAmistadToAttach.getClass(), amistadListNewAmistadToAttach.getId());
                attachedAmistadListNew.add(amistadListNewAmistadToAttach);
            }
            amistadListNew = attachedAmistadListNew;
            usuario.setAmistadList(amistadListNew);
            List<Amistad> attachedAmistadList1New = new ArrayList<Amistad>();
            for (Amistad amistadList1NewAmistadToAttach : amistadList1New) {
                amistadList1NewAmistadToAttach = em.getReference(amistadList1NewAmistadToAttach.getClass(), amistadList1NewAmistadToAttach.getId());
                attachedAmistadList1New.add(amistadList1NewAmistadToAttach);
            }
            amistadList1New = attachedAmistadList1New;
            usuario.setAmistadList1(amistadList1New);
            List<Mensaje> attachedMensajeListNew = new ArrayList<Mensaje>();
            for (Mensaje mensajeListNewMensajeToAttach : mensajeListNew) {
                mensajeListNewMensajeToAttach = em.getReference(mensajeListNewMensajeToAttach.getClass(), mensajeListNewMensajeToAttach.getId());
                attachedMensajeListNew.add(mensajeListNewMensajeToAttach);
            }
            mensajeListNew = attachedMensajeListNew;
            usuario.setMensajeList(mensajeListNew);
            List<Mensaje> attachedMensajeList1New = new ArrayList<Mensaje>();
            for (Mensaje mensajeList1NewMensajeToAttach : mensajeList1New) {
                mensajeList1NewMensajeToAttach = em.getReference(mensajeList1NewMensajeToAttach.getClass(), mensajeList1NewMensajeToAttach.getId());
                attachedMensajeList1New.add(mensajeList1NewMensajeToAttach);
            }
            mensajeList1New = attachedMensajeList1New;
            usuario.setMensajeList1(mensajeList1New);
            List<Comentario> attachedComentarioListNew = new ArrayList<Comentario>();
            for (Comentario comentarioListNewComentarioToAttach : comentarioListNew) {
                comentarioListNewComentarioToAttach = em.getReference(comentarioListNewComentarioToAttach.getClass(), comentarioListNewComentarioToAttach.getId());
                attachedComentarioListNew.add(comentarioListNewComentarioToAttach);
            }
            comentarioListNew = attachedComentarioListNew;
            usuario.setComentarioList(comentarioListNew);
            List<Publicacion> attachedPublicacionListNew = new ArrayList<Publicacion>();
            for (Publicacion publicacionListNewPublicacionToAttach : publicacionListNew) {
                publicacionListNewPublicacionToAttach = em.getReference(publicacionListNewPublicacionToAttach.getClass(), publicacionListNewPublicacionToAttach.getId());
                attachedPublicacionListNew.add(publicacionListNewPublicacionToAttach);
            }
            publicacionListNew = attachedPublicacionListNew;
            usuario.setPublicacionList(publicacionListNew);
            usuario = em.merge(usuario);
            for (Etiqueta etiquetaListOldEtiqueta : etiquetaListOld) {
                if (!etiquetaListNew.contains(etiquetaListOldEtiqueta)) {
                    etiquetaListOldEtiqueta.setUsuario(null);
                    etiquetaListOldEtiqueta = em.merge(etiquetaListOldEtiqueta);
                }
            }
            for (Etiqueta etiquetaListNewEtiqueta : etiquetaListNew) {
                if (!etiquetaListOld.contains(etiquetaListNewEtiqueta)) {
                    Usuario oldUsuarioOfEtiquetaListNewEtiqueta = etiquetaListNewEtiqueta.getUsuario();
                    etiquetaListNewEtiqueta.setUsuario(usuario);
                    etiquetaListNewEtiqueta = em.merge(etiquetaListNewEtiqueta);
                    if (oldUsuarioOfEtiquetaListNewEtiqueta != null && !oldUsuarioOfEtiquetaListNewEtiqueta.equals(usuario)) {
                        oldUsuarioOfEtiquetaListNewEtiqueta.getEtiquetaList().remove(etiquetaListNewEtiqueta);
                        oldUsuarioOfEtiquetaListNewEtiqueta = em.merge(oldUsuarioOfEtiquetaListNewEtiqueta);
                    }
                }
            }
            for (Amistad amistadListOldAmistad : amistadListOld) {
                if (!amistadListNew.contains(amistadListOldAmistad)) {
                    amistadListOldAmistad.setUsuario(null);
                    amistadListOldAmistad = em.merge(amistadListOldAmistad);
                }
            }
            for (Amistad amistadListNewAmistad : amistadListNew) {
                if (!amistadListOld.contains(amistadListNewAmistad)) {
                    Usuario oldUsuarioOfAmistadListNewAmistad = amistadListNewAmistad.getUsuario();
                    amistadListNewAmistad.setUsuario(usuario);
                    amistadListNewAmistad = em.merge(amistadListNewAmistad);
                    if (oldUsuarioOfAmistadListNewAmistad != null && !oldUsuarioOfAmistadListNewAmistad.equals(usuario)) {
                        oldUsuarioOfAmistadListNewAmistad.getAmistadList().remove(amistadListNewAmistad);
                        oldUsuarioOfAmistadListNewAmistad = em.merge(oldUsuarioOfAmistadListNewAmistad);
                    }
                }
            }
            for (Amistad amistadList1OldAmistad : amistadList1Old) {
                if (!amistadList1New.contains(amistadList1OldAmistad)) {
                    amistadList1OldAmistad.setAmistad(null);
                    amistadList1OldAmistad = em.merge(amistadList1OldAmistad);
                }
            }
            for (Amistad amistadList1NewAmistad : amistadList1New) {
                if (!amistadList1Old.contains(amistadList1NewAmistad)) {
                    Usuario oldAmistadOfAmistadList1NewAmistad = amistadList1NewAmistad.getAmistad();
                    amistadList1NewAmistad.setAmistad(usuario);
                    amistadList1NewAmistad = em.merge(amistadList1NewAmistad);
                    if (oldAmistadOfAmistadList1NewAmistad != null && !oldAmistadOfAmistadList1NewAmistad.equals(usuario)) {
                        oldAmistadOfAmistadList1NewAmistad.getAmistadList1().remove(amistadList1NewAmistad);
                        oldAmistadOfAmistadList1NewAmistad = em.merge(oldAmistadOfAmistadList1NewAmistad);
                    }
                }
            }
            for (Mensaje mensajeListNewMensaje : mensajeListNew) {
                if (!mensajeListOld.contains(mensajeListNewMensaje)) {
                    Usuario oldEnviaOfMensajeListNewMensaje = mensajeListNewMensaje.getEnvia();
                    mensajeListNewMensaje.setEnvia(usuario);
                    mensajeListNewMensaje = em.merge(mensajeListNewMensaje);
                    if (oldEnviaOfMensajeListNewMensaje != null && !oldEnviaOfMensajeListNewMensaje.equals(usuario)) {
                        oldEnviaOfMensajeListNewMensaje.getMensajeList().remove(mensajeListNewMensaje);
                        oldEnviaOfMensajeListNewMensaje = em.merge(oldEnviaOfMensajeListNewMensaje);
                    }
                }
            }
            for (Mensaje mensajeList1NewMensaje : mensajeList1New) {
                if (!mensajeList1Old.contains(mensajeList1NewMensaje)) {
                    Usuario oldRecibeOfMensajeList1NewMensaje = mensajeList1NewMensaje.getRecibe();
                    mensajeList1NewMensaje.setRecibe(usuario);
                    mensajeList1NewMensaje = em.merge(mensajeList1NewMensaje);
                    if (oldRecibeOfMensajeList1NewMensaje != null && !oldRecibeOfMensajeList1NewMensaje.equals(usuario)) {
                        oldRecibeOfMensajeList1NewMensaje.getMensajeList1().remove(mensajeList1NewMensaje);
                        oldRecibeOfMensajeList1NewMensaje = em.merge(oldRecibeOfMensajeList1NewMensaje);
                    }
                }
            }
            for (Comentario comentarioListOldComentario : comentarioListOld) {
                if (!comentarioListNew.contains(comentarioListOldComentario)) {
                    comentarioListOldComentario.setUsuario(null);
                    comentarioListOldComentario = em.merge(comentarioListOldComentario);
                }
            }
            for (Comentario comentarioListNewComentario : comentarioListNew) {
                if (!comentarioListOld.contains(comentarioListNewComentario)) {
                    Usuario oldUsuarioOfComentarioListNewComentario = comentarioListNewComentario.getUsuario();
                    comentarioListNewComentario.setUsuario(usuario);
                    comentarioListNewComentario = em.merge(comentarioListNewComentario);
                    if (oldUsuarioOfComentarioListNewComentario != null && !oldUsuarioOfComentarioListNewComentario.equals(usuario)) {
                        oldUsuarioOfComentarioListNewComentario.getComentarioList().remove(comentarioListNewComentario);
                        oldUsuarioOfComentarioListNewComentario = em.merge(oldUsuarioOfComentarioListNewComentario);
                    }
                }
            }
            for (Publicacion publicacionListOldPublicacion : publicacionListOld) {
                if (!publicacionListNew.contains(publicacionListOldPublicacion)) {
                    publicacionListOldPublicacion.setUsuario(null);
                    publicacionListOldPublicacion = em.merge(publicacionListOldPublicacion);
                }
            }
            for (Publicacion publicacionListNewPublicacion : publicacionListNew) {
                if (!publicacionListOld.contains(publicacionListNewPublicacion)) {
                    Usuario oldUsuarioOfPublicacionListNewPublicacion = publicacionListNewPublicacion.getUsuario();
                    publicacionListNewPublicacion.setUsuario(usuario);
                    publicacionListNewPublicacion = em.merge(publicacionListNewPublicacion);
                    if (oldUsuarioOfPublicacionListNewPublicacion != null && !oldUsuarioOfPublicacionListNewPublicacion.equals(usuario)) {
                        oldUsuarioOfPublicacionListNewPublicacion.getPublicacionList().remove(publicacionListNewPublicacion);
                        oldUsuarioOfPublicacionListNewPublicacion = em.merge(oldUsuarioOfPublicacionListNewPublicacion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = usuario.getId();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Mensaje> mensajeListOrphanCheck = usuario.getMensajeList();
            for (Mensaje mensajeListOrphanCheckMensaje : mensajeListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Mensaje " + mensajeListOrphanCheckMensaje + " in its mensajeList field has a non-nullable envia field.");
            }
            List<Mensaje> mensajeList1OrphanCheck = usuario.getMensajeList1();
            for (Mensaje mensajeList1OrphanCheckMensaje : mensajeList1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Mensaje " + mensajeList1OrphanCheckMensaje + " in its mensajeList1 field has a non-nullable recibe field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Etiqueta> etiquetaList = usuario.getEtiquetaList();
            for (Etiqueta etiquetaListEtiqueta : etiquetaList) {
                etiquetaListEtiqueta.setUsuario(null);
                etiquetaListEtiqueta = em.merge(etiquetaListEtiqueta);
            }
            List<Amistad> amistadList = usuario.getAmistadList();
            for (Amistad amistadListAmistad : amistadList) {
                amistadListAmistad.setUsuario(null);
                amistadListAmistad = em.merge(amistadListAmistad);
            }
            List<Amistad> amistadList1 = usuario.getAmistadList1();
            for (Amistad amistadList1Amistad : amistadList1) {
                amistadList1Amistad.setAmistad(null);
                amistadList1Amistad = em.merge(amistadList1Amistad);
            }
            List<Comentario> comentarioList = usuario.getComentarioList();
            for (Comentario comentarioListComentario : comentarioList) {
                comentarioListComentario.setUsuario(null);
                comentarioListComentario = em.merge(comentarioListComentario);
            }
            List<Publicacion> publicacionList = usuario.getPublicacionList();
            for (Publicacion publicacionListPublicacion : publicacionList) {
                publicacionListPublicacion.setUsuario(null);
                publicacionListPublicacion = em.merge(publicacionListPublicacion);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
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

    public Usuario findUsuario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
