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
    <style type="text/css">
    .bs-example {
    	font-family: sans-serif;
    	position: relative;
    	margin: 100px;
    }
    .typeahead, .tt-query, .tt-hint {
    	width: 396px;
    }
    .typeahead {
    	background-color: #FFFFFF;
    }
    .typeahead:focus {
    	border: 2px solid #0097CF;
    }
    .tt-query {
    	box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset;
    }
    .tt-hint {
    	color: #999999;
    }
    .tt-menu {
    	background-color: #FFFFFF;
    	border: 1px solid rgba(0, 0, 0, 0.2);
    	border-radius: 8px;
    	box-shadow: 0 5px 10px rgba(0, 0, 0, 0.2);
    	margin-top: 12px;
    	padding: 8px 0;
    	width: 422px;
    }
    .tt-suggestion {
    	font-size: 22px;  /* Set suggestion dropdown font size */
    	padding: 3px 20px;
    }
    .tt-suggestion:hover {
    	cursor: pointer;
    	background-color: #0097CF;
    	color: #FFFFFF;
    }
    .tt-suggestion p {
    	margin: 0;
    }
    .item-separator {
        border-bottom: 1px dashed #0097CF;
    }
    .thin-border {
        border: 1px solid #ced4da;
        margin: 0 15px 0 30px;
        padding: 1px 1px 1px 12px;
    }
    </style>
    </head>
    <body>
        <jsp:include page="../layout/_navbar.jsp">
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
                                <div class="form-group">
                                <label for="exampleFormControlInput1">Find existing customer: </label>
                                <input type="text" class="form-control typeahead" autocomplete="off" spellcheck="false" placeholder="name@example.com">
                                </div>
                                <button type="button" class="btn btn-link" data-toggle="modal" data-target="#customer-form-modal">
                                  + Add Customer
                                </button>
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
    		            <div class="col-md-7">
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
    		            <div class="col-md-2 font-weight-bold">
    		                <div>
    		                    Tax
    		                </div>
    		            </div>
    		        </div>
    		        <div class="row">
                        <div class="col-md">
                            <hr>
                        </div>
                    </div>


    		        <div class="row" id="line-item-form-row-1">
                        <div class="col-md">
                          <div class="form-row mb-2">
                            <div class="col-7">
                                <input name="invoiceItems[0].productName" type="text" class="form-control" id="product-name-input-1" autocomplete="off" spellcheck="false" placeholder="Product or service">
                            </div>
                            <div class="col-1">
                                <input name="invoiceItems[0].itemQuantity" type="text" class="form-control" id="item-quantity-input-1" placeholder="Qty">
                            </div>
                            <div class="col-2">
                                <div class="input-group">
                                    <div class="input-group-prepend">
                                      <div class="input-group-text">$</div>
                                    </div>
                                    <input name="invoiceItems[0].unitPrice" type="text" class="form-control" id="item-unit-price-input-1" placeholder="Unit price">
                                </div>
                            </div>
                            <div class="col-2">
                                <div class="input-group">
                                    <input name="invoiceItems[0].taxRate" type="text" class="form-control" id="tax-rate-input-1" placeholder="Tax Rate">
                                    <div class="input-group-append">
                                      <div class="input-group-text">%</div>
                                    </div>
                                </div>
                            </div>
                          </div>
                         <div class="row">
                              <div class="col-md-12">
                                  <textarea name="invoiceItems[0].productDescription" id="description-input-1" class="form-control" placeholder="Provide a brief description of your product or service"></textarea>
                              </div>
                          </div>

                        </div>
                    </div>
                    <div class="row">
                    	<div class="col-12">
                          <div class="btn-group mt-2" role="group" aria-label="Row Button Bar">
								 <button type="button" class="btn btn-success" id="add-item-row-btn">+ Add</button>
								 <button type="button" class="btn btn-danger" id="remove-item-row-btn">- Remove</button>
							</div>
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
                                        <div class="col-md thin-border" id="invoice-subtotal"></div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12 mb-2">
                                    <div class="row">
                                        <div class="col-md font-weight-bold">TAX</div>
                                        <div class="col-md thin-border" id="invoice-tax"></div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12 mb-2">
                                    <div class="row">
                                        <div class="col-md font-weight-bold">TOTAL</div>
                                        <div class="col-md thin-border" id="invoice-total"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr>
                    <button type="submit" class="btn btn-primary float-right" id="invoice-save-btn">Preview Invoice</button>
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
                <jsp:include page="../customer/_customer_form.jsp"/>
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
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/services/invoice-service.js"></script>
        <script type="text/javascript">











        </script>
    </body>
</html>
