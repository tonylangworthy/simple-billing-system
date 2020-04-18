<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">
    <head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/webjars/bootstrap/4.3.1/css/bootstrap.min.css" />

    <c:url value="${pageContext.request.contextPath}/css/main.css" var="jstlCss" />
    <link type="text/css" href="${typeaheadCss}" rel="stylesheet" />
    <c:url value="${pageContext.request.contextPath}/css/typeahead.css" var="typeaheadCss" />
    <link type="text/css" href="${typeaheadCss}" rel="stylesheet" />
    </head>
    <body>
        <jsp:include page="_navbar.jsp">
            <jsp:param name="userName" value="${userName}"/>
        </jsp:include>
    	<div class="container">
        <div class="invoice-create-form mx-auto">
    		<div class="card">
    		    <div class="card-header">Create Invoice</div>
    		    <div class="card-body">
                <form action="/invoices" method="post" autocomplete="off">
                    <input type="hidden" name="customerId" id="customer-id-input" value="" />
    		        <div class="row">
    		            <div class="col-md">
                            <div>
                                <address>
                                    <strong>${invoice.accountCompany}</strong><br>
                                    ${invoice.accountAddress}<br>
                                    ${invoice.accountCity}, ${invoice.accountState} ${invoice.accountZip}<br>
                                    ${invoice.accountEmail}<br>
                                    ${invoice.accountPhone}<br>
                                    ${invoice.accountWebsite}
                                </address>
                            </div>
                            <div>
                                <div id="bill-to"></div>

                            </div>
    		            </div>
    		            <div class="col-md text-md-right">
    		                <h3>INVOICE</h3>
    		            </div>

    		        </div>
    		        <div class="row">
    		            <div class="col-md">
    		                <hr>
    		            </div>
    		        </div>
    		        <div class="row saved-line-item">
    		            <div class="col-md-9">
    		                <div class="pl-1 font-weight-bold">
    		                    Product or Service
    		                </div>
    		            </div>
    		            <div class="col-md-1 font-weight-bold">
    		                <div>
    		                    Qty
    		                </div>
    		            </div>
    		            <div class="col-md-2 font-weight-bold">
    		                <div>
    		                    Unit Price
    		                </div>
    		            </div>
    		        </div>
    		        <div class="row">
                        <div class="col-md">
                            <hr>
                        </div>
                    </div>


    		        <div class="row" id="line-item-form-row">
                        <div class="col-md">
                          <div class="form-row mb-2">
                            <div class="col-9">
                                <input name="lineItemName" type="text" class="form-control" id="product-name-input" autocomplete="off" spellcheck="false" placeholder="Product or service">
                            </div>
                            <div class="col-1">
                                <input name="lineItemQty" type="text" class="form-control" id="item-quantity-input" placeholder="Qty">
                            </div>
                            <div class="col-2">
                                <div class="input-group">
                                    <div class="input-group-prepend">
                                      <div class="input-group-text">$</div>
                                    </div>
                                    <input name="lineItemUnitPrice" type="text" class="form-control" id="item-unit-price-input" placeholder="Unit price">
                                </div>
                            </div>
                          </div>
                         <div class="row">
                              <div class="col-md-12">
                                  <textarea name="lineItemDescription" id="description-input" class="form-control" placeholder="Provide a brief description of your product or service" required></textarea>
                              </div>
                          </div>

                          <button type="button" class="btn btn-light mt-2" id="add-item-row-btn">Save to Invoice</button>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-6 mt-3">
                            <div class="form-group">
                                <label for="invoice-note-input">Note to customer</label>
                                <textarea name="invoiceNote" class="form-control" id="invoice-note-input" rows="3"></textarea>
                            </div>
                        </div>
                        <div class="col-3 offset-3">
                            <div class="row">
                                <div class="col-md-12 mb-2">
                                    <div class="row">
                                        <div class="col-md font-weight-bold">SUBTOTAL</div>
                                        <div class="col-md thin-border">0.00</div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12 mb-2">
                                <div class="row">
                                    <div class="col-md font-weight-bold">TAX RATE</div>
                                    <div class="col-md">
                                    <div class="input-group input-group-sm">
                                        <div class="input-group-prepend">
                                          <div class="input-group-text">$</div>
                                        </div>
                                        <input name="lineItemAmount" type="text" class="form-control" placeholder="Tax Rate">
                                    </div>
                                    </div>
                                </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12 mb-2">
                                    <div class="row">
                                        <div class="col-md font-weight-bold">TAX</div>
                                        <div class="col-md thin-border">0.00</div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12 mb-2">
                                    <div class="row">
                                        <div class="col-md font-weight-bold">TOTAL</div>
                                        <div class="col-md thin-border">0.00</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr>
                    <button type="button" class="btn btn-primary float-right" id="finalize-btn">Create Invoice</button>
                </form>

    		    </div>
    		</div>





        </div>
    	</div>

        <!-- Modal -->
        <div class="modal fade" id="customer-form-modal" tabindex="-1" role="dialog" aria-labelledby="customer-form-label" aria-hidden="true">
          <div class="modal-dialog" role="document">
            <div class="modal-content">
            <form id="customer-form" method="post" action="/customers">
              <div class="modal-header">
                <h5 class="modal-title" id="customer-form-label">Add New Customer</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span>
                </button>
              </div>
              <div class="modal-body">
                <jsp:include page="_customer_form.jsp"/>
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary" id="customer-save-btn">Save changes</button>
              </div>
            </div>
            </form>
          </div>
        </div>
    	<script type="text/javascript" src="${pageContext.request.contextPath}/webjars/jquery/3.0.0/jquery.min.js"></script>
    	<script type="text/javascript" src="${pageContext.request.contextPath}/webjars/typeahead.js/0.11.1/dist/typeahead.bundle.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        <script type="text/javascript">

        </script>
    </body>
</html>
