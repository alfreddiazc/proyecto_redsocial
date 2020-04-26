package VO;

import java.io.InputStream;

public class ImagenVO {

    /*Todo los atributos*/
    int id;
    String nombre;
    InputStream archivo;
    private byte[] archivoimg2;

    public ImagenVO(int id, String name, byte[] archivo) {
        this.id = id;
        this.nombre = name;
        this.archivoimg2 = archivo;
    }

    public ImagenVO() {
    }

    /*Todo los codigos get*/
    public int getCodigoimg() {
        return id;
    }

    public String getNombreimg() {
        return nombre;
    }

    public InputStream getArchivoimg() {
        return archivo;
    }


    /*Todo los codigos set*/
    public void setCodigoimg(int codigoimg) {
        this.id = codigoimg;
    }

    public void setNombreimg(String nombreimg) {
        this.nombre = nombreimg;
    }

    public void setArchivoimg(InputStream archivoimg) {
        this.archivo = archivoimg;
    }

    /**
     * @return the archivopdf2
     */
    public byte[] getArchivoimg2() {
        return archivoimg2;
    }

    /**
     * @param archivopdf2 the archivopdf2 to set
     */
    public void setArchivoimg2(byte[] archivoimg2) {
        this.archivoimg2 = archivoimg2;
    }

}
