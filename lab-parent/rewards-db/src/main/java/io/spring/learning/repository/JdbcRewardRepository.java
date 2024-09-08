package io.spring.learning.repository;

import io.spring.learning.common.datetime.SimpleDate;
import io.spring.learning.dto.AccountContribution;
import io.spring.learning.dto.Dining;
import io.spring.learning.dto.RewardConfirmation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class JdbcRewardRepository implements RewardRepository {

    public static final String TYPE ="jdbc";

    private static final Logger LOGGER = LoggerFactory.getLogger("config");

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcRewardRepository(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        LOGGER.info("Created JdbcRewardRepository");
    }

    @Override
    public String getInfo() {
        return TYPE;
    }

    @Override
    public RewardConfirmation confirmReward(AccountContribution contribution, Dining dining) {
        String sql = "insert into T_REWARD (CONFIRMATION_NUMBER, REWARD_AMOUNT, REWARD_DATE, " +
                "ACCOUNT_NUMBER, DINING_MERCHANT_NUMBER, DINING_DATE, DINING_AMOUNT) values (?,?," +
                "?,?,?,?,?)";
        String confirmationNumber = nextConfirmationNumber();
        jdbcTemplate.update(sql, confirmationNumber, contribution.getAmount().asBigDecimal(),
                SimpleDate.today().asDate(), contribution.getAccountNumber(),
                dining.getMerchantNumber(), dining.getDate().asDate(),
                dining.getMonetaryAmount().asBigDecimal());
        return new RewardConfirmation(confirmationNumber, contribution);
    }

    private String nextConfirmationNumber(){
        String sql = "select ZERO as S_REWARD_CONFIRMATION_NUMBER from " +
                "DUAL_REWARD_CONFIRMATION_NUMBER";
        return jdbcTemplate.queryForObject(sql, String.class);
    }
}
