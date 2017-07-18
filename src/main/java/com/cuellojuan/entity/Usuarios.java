package com.cuellojuan.entity;

    public class Usuarios {

        public Integer id;
        public String nombrecompleto;
        public String ubicacion;
        public String telefono;
        public String usuarionombre;
        public String contrasena;
        public String categoria;

        public Usuarios() {
        }

        public Usuarios(Integer usuarioid) {
            this.id = usuarioid;
        }

        public Usuarios(Integer id, String nombrecompleto, String ubicacion, String telefono, String usuarionombre, String contrasena, String categoria) {
            this.id = id;
            this.nombrecompleto = nombrecompleto;
            this.ubicacion = ubicacion;
            this.telefono = telefono;
            this.usuarionombre = usuarionombre;
            this.contrasena = contrasena;
            this.categoria = categoria;
        }

        public Integer getid() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getNombrecompleto() {
            return nombrecompleto;
        }

        public void setNombrecompleto(String nombrecompleto) {
            this.nombrecompleto = nombrecompleto;
        }

        public String getUbicacion() {
            return ubicacion;
        }

        public void setUbicacion(String ubicacion) {
            this.ubicacion = ubicacion;
        }

        public String getTelefono() {
            return telefono;
        }

        public void setTelefono(String telefono) {
            this.telefono = telefono;
        }

        public String getUsuarionombre() {
            return usuarionombre;
        }

        public void setUsuarionombre(String usuarionombre) {
            this.usuarionombre = usuarionombre;
        }

        public String getContrasena() {
            return contrasena;
        }

        public void setContrasena(String contrasena) {
            this.contrasena = contrasena;
        }

        public String getCategoria() {
            return categoria;
        }

        public void setCategoria(String categoria) {
            this.categoria = categoria;
        }

        @Override
        public int hashCode() {
            int hash = 0;
            hash += (id != null ? id.hashCode() : 0);
            return hash;
        }

        @Override
        public boolean equals(Object object) {
            // TODO: Warning - this method won't work in the case the id fields are not set
            if (!(object instanceof Usuarios)) {
                return false;
            }
            Usuarios other = (Usuarios) object;
            if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
                return false;
            }
            return true;
        }

        @Override
        public String toString() {
            return "UsuarioID=" + id + " ]";
        }

    }


