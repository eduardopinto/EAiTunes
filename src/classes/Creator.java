/* classe utilizada a nível conceptual para o padrao factory method */
package classes;

public abstract class Creator {
    public abstract Content factoryMethod(String contentType, Object[] content) throws Exception;
}
