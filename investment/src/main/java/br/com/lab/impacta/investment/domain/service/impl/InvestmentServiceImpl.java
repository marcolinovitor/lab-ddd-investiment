package br.com.lab.impacta.investment.domain.service.impl;


import br.com.lab.impacta.investment.domain.exception.InvestmentProductNotFoundException;
import br.com.lab.impacta.investment.domain.exception.NotEnoughBalanceException;
import br.com.lab.impacta.investment.domain.model.Investment;
import br.com.lab.impacta.investment.domain.model.Product;
import br.com.lab.impacta.investment.domain.service.InvestmentService;
import br.com.lab.impacta.investment.domain.service.facade.AccountFacade;
import br.com.lab.impacta.investment.domain.service.facade.valueObject.AccountBalanceVO;
import br.com.lab.impacta.investment.infrastructure.repository.InvestmentRepository;
import br.com.lab.impacta.investment.infrastructure.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InvestmentServiceImpl implements InvestmentService {
    @Autowired
    private InvestmentRepository investmentRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AccountFacade accountFacade;

    @Value("${lab.investment.exception.product-dont-exists-description}")
    private String DESCRIPTION_PRODUCT_NOT_FOUND_EX;
    @Value("${lab.investment.exception.product-dont-exists-message}")
    private String MESSAGE_PRODUCT_NOT_FOUND_EX;

    @Value("${lab.investment.exception.account-without-balance-description}")
    private String DESCRIPTION_NOT_ENOUGH_BALANCE_EX;
    @Value("${lab.investment.exception.account-without-balance-message}")
    private String MESSAGE_NOT_ENOUGH_BALANCE_EX;


    @Override
    public Investment invest(Long productId, Long accountId, Double investmentValue) {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isEmpty()) {
            throw new InvestmentProductNotFoundException(
                    DESCRIPTION_PRODUCT_NOT_FOUND_EX,
                    MESSAGE_PRODUCT_NOT_FOUND_EX
            );
        }

        Investment investment = new Investment(productId, accountId, investmentValue);
        AccountBalanceVO accountBalanceVO = accountFacade.getAccountBalanceById(accountId);

        if (!investment.enoughBalanceForInvestment(accountBalanceVO.getBalance())) {
            throw new NotEnoughBalanceException(
                    DESCRIPTION_NOT_ENOUGH_BALANCE_EX,
                    MESSAGE_NOT_ENOUGH_BALANCE_EX
            );
        }

        return null;
    }
}
