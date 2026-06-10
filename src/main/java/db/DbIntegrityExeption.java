package db;

/**
 * EXCEÇÃO DE INTEGRIDADE REFERENCIAL
 * * CONCEITO DIDÁTICO: Criada especificamente para tratar erros de Chave Estrangeira (FK).
 * Se o aluno tentar deletar um Departamento que ainda possui Vendedores vinculados,
 * o MySQL vai barrar. Nós capturamos esse erro e lançamos esta exceção para tratá-la de forma amigável na tela.
 */
public class DbIntegrityExeption extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public DbIntegrityExeption(String msg){

        super(msg);
    }

}