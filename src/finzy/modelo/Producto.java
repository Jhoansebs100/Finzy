package finzy.modelo;

import finzy.modelo.Timestamp;
import finzy.modelo.Transaccion;

public class Producto {
    private String codigo;
    private double saldo;
    
    public Transaccion transaccion(double monto,String concepto){
        Transaccion transaccion_ = new Transaccion(monto,concepto);
        return transaccion_;
    }
    
    public Extracto extracto(Timestamp desde,Timestamp hasta){
        Extracto extracto_ = new Extracto(desde,hasta);
        return extracto_;
    }
    public void crearMetaAhorro(){
        
    }
    
    public double getSaldo(){
        return this.saldo;
    }
    

}
