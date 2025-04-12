package com.meli.cms.comtroller;

import com.meli.cms.carrier.BranchAddCarrier;
import com.meli.cms.carrier.CustomerRegisterCarrier;
import com.meli.cms.carrier.DepositCreateCarrier;
import com.meli.cms.emf.JpaUtil;
import com.meli.cms.entity.Branch;
import com.meli.cms.entity.CardType;
import com.meli.cms.entity.Customer;
import com.meli.cms.entity.Deposit;
import com.meli.cms.repository.BranchRepository;
import com.meli.cms.repository.CardRepository;
import com.meli.cms.repository.CustomerRepository;
import com.meli.cms.repository.DepositRepository;
import com.meli.cms.service.BranchService;
import com.meli.cms.service.CardService;
import com.meli.cms.service.CustomerService;
import com.meli.cms.service.DepositService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;


@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    private CustomerService customerService;
    private CardService cardService;
    private BranchService branchService;
    private DepositService depositService;

    @Override
    public void init() throws ServletException {

        this.customerService = new CustomerService(new CustomerRepository());
        this.cardService = new CardService(new CardRepository(),
                new CustomerService(new CustomerRepository()),
                new BranchService(new BranchRepository()),
                new DepositService(new DepositRepository()));
        this.branchService = new BranchService(new BranchRepository());
        this.depositService = new DepositService(new DepositRepository());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String nationalId = request.getParameter("national_id");
        String cardTypeStr = request.getParameter("cardtype");
        CardType cardType = CardType.valueOf(cardTypeStr);
        String location = request.getParameter("location");
        double amount = Double.parseDouble(request.getParameter("amount"));

        Customer customer = customerService.registerCustomer(new CustomerRegisterCarrier(firstName, lastName, nationalId));
        UUID customerId = customer.getCustomerId();
        customerService.getCustomer(customerId);

        Branch branch = branchService.addBranch(new BranchAddCarrier(location));
        UUID branchId = branch.getBranchId();
        branchService.getBranch(branchId);

        Deposit deposit = depositService.createDeposit(new DepositCreateCarrier(amount));
        UUID depositId = deposit.getDepositId();
        depositService.getDeposit(depositId);

        cardService.createCard(customerId, cardType, branchId, depositId);

        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println("<p style='font-weight:bold; font-size:30px; color:black;'>ثبت نام شما با موفقیت انجام شد</p>");
    }

    @Override
    public void destroy() {
        JpaUtil.closeFactory();
    }
}