package utils;

import ch.qos.logback.classic.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.IllegalTransactionStateException;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class TransactionUtils {

    protected final PlatformTransactionManager transactionManager;

    protected final Logger LOGGER;


    private TransactionStatus transactionStatus;

    public TransactionUtils (PlatformTransactionManager transactionManager){
        assert(transactionManager != null);

        this.transactionManager = transactionManager;

        LOGGER = LoggerFactory.getLogger(getClass());
        if(LOGGER instanceof ch.qos.logback.classic.Logger)
            ((ch.qos.logback.classic.Logger) LOGGER).setLevel(Level.INFO);
    }

    public void beginTransaction() throws Exception{
        try{
            transactionStatus =
                    transactionManager.getTransaction(new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_MANDATORY));
            assert(false);
        }catch (IllegalTransactionStateException ex){

        }

        transactionStatus =
                transactionManager.getTransaction(new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRED));

        assert (transactionStatus != null);
        assert (transactionStatus.isNewTransaction());
        LOGGER.info("NEW " + transactionStatus + " - Completed " + transactionStatus.isCompleted());
    }

    public void rollbackTransaction(){
        try{
            transactionManager.getTransaction(new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_MANDATORY));
        }catch (IllegalTransactionStateException ex){
            assert (false);
        }
        LOGGER.info("ROLLBACK" + transactionStatus);
        transactionManager.rollback(transactionStatus);
    }

    public TransactionStatus getCurrentTransaction(){
        TransactionDefinition definition =
                new DefaultTransactionDefinition(DefaultTransactionDefinition.PROPAGATION_MANDATORY);
        TransactionStatus transaction = transactionManager.getTransaction(definition);
        LOGGER.info("TRANSACTION = "+ transaction);
        return transaction;
    }

    public TransactionStatus getTransaction(){
        TransactionDefinition definition =
                new DefaultTransactionDefinition(DefaultTransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus transaction = transactionManager.getTransaction(definition);
        LOGGER.info("TRANSACTION = "+ transaction);
        return transaction;
    }

    public TransactionStatus getNewTransaction(){
        TransactionDefinition definition =
                new DefaultTransactionDefinition(DefaultTransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus transaction = transactionManager.getTransaction(definition);
        LOGGER.info("TRANSACTION = "+ transaction);
        return transaction;
    }

    public boolean transactionExists() {
        try {
            TransactionStatus transaction = getCurrentTransaction();

            if (transaction == null)
                throw new IllegalStateException("No transaction in progress");

            LOGGER.info("TRANSACTION EXISTS - new ? " + transaction.isNewTransaction());
            return true;
        } catch (Exception e) {
            LOGGER.error("NO TRANSACTION: " + e);
            return false;
        }
    }
}
