package finzy.modelo;

public class Timestamp {
    private int anho;
    private int mes;
    private int dia;
    private Integer hora;
    private Integer min;
    
    public Timestamp(int dia,int mes,int anho){
        this.dia=dia;
        this.anho=mes;
        this.anho=anho;
    }
    public Timestamp(int dia,int mes,int anho,int hora,int min){
        this.dia=dia;
        this.anho=mes;
        this.anho=anho;
        this.hora=hora;
        this.min=min;
    }
    
    public String ISO8601(){
        if(this.hora==null){
            return String.valueOf(anho)+String.valueOf(mes)+String.valueOf(dia);
        }
        else{
            return String.valueOf(anho)+String.valueOf(mes)+String.valueOf(dia)+String.valueOf(hora)+String.valueOf(min);
        }
    }
}
