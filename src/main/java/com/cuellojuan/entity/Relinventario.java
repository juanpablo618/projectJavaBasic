package com.cuellojuan.entity;


import java.util.Date;

public class Relinventario {

    public int idbeneinventario ;
    public String	idappartamento;
    public int	idmagazzino;
    public int	quantita;
    public int	quantita_min_predef	;
	public String 	richiesto_checkin;
	public Date datainserimento	;
	public String	hostinserimento	;
	public int	utente_inserimento;


    public Relinventario() {
    }

    public Relinventario(int idbeneinventario, String idappartamento, int idmagazzino, int quantita, int quantita_min_predef, String richiesto_checkin, Date datainserimento, String hostinserimento, int utente_inserimento) {
        this.idbeneinventario = idbeneinventario;
        this.idappartamento = idappartamento;
        this.idmagazzino = idmagazzino;
        this.quantita = quantita;
        this.quantita_min_predef = quantita_min_predef;
        this.richiesto_checkin = richiesto_checkin;
        this.datainserimento = datainserimento;
        this.hostinserimento = hostinserimento;
        this.utente_inserimento = utente_inserimento;
    }


    public Date getDatainserimento() {
        return datainserimento;
    }

    public void setDatainserimento(Date datainserimento) {
        this.datainserimento = datainserimento;
    }

    public int getIdbeneinventario() {
        return idbeneinventario;
    }



    public String getIdappartamento() {
        return idappartamento;
    }

    public void setIdappartamento(String idappartamento) {
        this.idappartamento = idappartamento;
    }

    public void setIdbeneinventario(int idbeneinventario) {
        this.idbeneinventario = idbeneinventario;
    }


    public int getIdmagazzino() {
        return idmagazzino;
    }

    public void setIdmagazzino(int idmagazzino) {
        this.idmagazzino = idmagazzino;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public int getQuantita_min_predef() {
        return quantita_min_predef;
    }

    public void setQuantita_min_predef(int quantita_min_predef) {
        this.quantita_min_predef = quantita_min_predef;
    }

    public String getRichiesto_checkin() {
        return richiesto_checkin;
    }

    public void setRichiesto_checkin(String richiesto_checkin) {
        this.richiesto_checkin = richiesto_checkin;
    }


    public String getHostinserimento() {
        return hostinserimento;
    }

    public void setHostinserimento(String hostinserimento) {
        this.hostinserimento = hostinserimento;
    }

    public int getUtente_inserimento() {
        return utente_inserimento;
    }

    public void setUtente_inserimento(int utente_inserimento) {
        this.utente_inserimento = utente_inserimento;
    }


    @Override
    public String toString() {
        return "Relinventario{" +
                "idbeneinventario=" + idbeneinventario +
                ", idappartamento='" + idappartamento + '\'' +
                ", idmagazzino=" + idmagazzino +
                ", quantita=" + quantita +
                ", quantita_min_predef=" + quantita_min_predef +
                ", richiesto_checkin='" + richiesto_checkin + '\'' +
                ", datainserimento=" + datainserimento +
                ", hostinserimento='" + hostinserimento + '\'' +
                ", utente_inserimento=" + utente_inserimento +
                '}';
    }
}
