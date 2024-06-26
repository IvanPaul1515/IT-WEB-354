package org.example.util;


public class ServerlessHelper {

    /**
     * Enum de variables.
     */
    public enum VariableAmbiente{

        TABLA("TABLA_SOLICITUD");

        private String valor;

        VariableAmbiente(String valor) {
            this.valor = valor;
        }

        public String getValor() {
            return valor;
        }
    }

    /**
     * Leyendo la tabla desde la variable de ambiente.
     * @return
     */
    public static String getNombreTabla(){
        return System.getenv(VariableAmbiente.TABLA.getValor());
    }
}