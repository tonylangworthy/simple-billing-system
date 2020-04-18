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
                        <div class="col-md-3 offset-9 mb-2">
                            <div class="row">
                                <div class="col-md font-weight-bold">SUBTOTAL</div>
                                <div class="col-md thin-border">0.00</div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-3 offset-9 mb-2">
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
                        <div class="col-md-3 offset-9 mb-2">
                            <div class="row">
                                <div class="col-md font-weight-bold">TAX</div>
                                <div class="col-md thin-border">0.00</div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-3 offset-9 mb-2">
                            <div class="row">
                                <div class="col-md font-weight-bold">TOTAL</div>
                                <div class="col-md thin-border">0.00</div>
                            </div>
                        </div>
                    </div>
                    <hr>
                    <button type="button" class="btn btn-primary float-right" id="finalize-btn">Finalize Invoice</button>
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

    class Invoice {

        constructor() {}

        invoice = {
            customerId: "",
            invoiceItems: []
        };

        invoiceItem = {
            productId: "",
            productName: "",
            productDescription: "",
            itemQuantity: "",
            unitPrice: ""
        };

        createLineItemObject() {

            this.invoiceItem.productName = $('#product-name-input').val()
            this.invoiceItem.productDescription = $('#description-input').val()
            this.invoiceItem.itemQuantity = $('#item-quantity-input').val()
            this.invoiceItem.unitPrice = $('#item-unit-price-input').val()

            this.invoice.invoiceItems.push(this.invoiceItem);

            console.log('Created line item object!!');
            console.log(this.invoice);

        } // end createLineItemObject method

        displayLineItem() {
            $(this.appendSavedLineItem()).insertBefore('#line-item-form-row');

           $('#product-name-input').val('')
           $('#description-input').val('')
           $('#item-quantity-input').val('')
           $('#item-unit-price-input').val('')

            //this.invoiceItem.productId = null;
            //this.invoiceItem.productName = null;
            //this.invoiceItem.productDescription = null;
            //this.invoiceItem.itemQuantity = null;
            //this.invoiceItem.unitPrice = null;

        } // end displayLineItem method

        saveInvoice() {
            console.info('Saving invoice: ' + JSON.stringify(this.invoice));
           let jqxhr = $.ajax({
               url: "/invoices",
               method: "post",
               contentType: "application/json",
               data: JSON.stringify(this.invoice)
           })
           .done(function(data) {

             console.log(data);

           })
           .fail(function() {
             console.log( "error" );
           })
           .always(function() {
             console.log( "complete" );

           });

        } // end saveInvoice method

        appendSavedLineItem() {
            let lineItem = '<div class="row saved-line-item">'
               + '<div class="col-md-9">'
                    + '<div class="pb-2">'
                        + '<strong>'+this.invoiceItem.productName+'</strong>'
                    + '</div>'
                + '</div>'
                + '<div class="col-md-1">'
                    + '<div class="pb-2">'+this.invoiceItem.itemQuantity+'</div>'
                + '</div>'
                + '<div class="col-md-2">'
                    + '<div class="pb-2">'+this.invoiceItem.unitPrice+'</div>'
                + '</div>'
            + '</div>'
            + '<div class="row">'
                   + '<div class="col-md">'
                       + '<small class="text-muted">'+this.invoiceItem.productDescription+'</small>'
                   + '</div>'
               + '</div>'
            + '<div class="row">'
                   + '<div class="col-md">'
                       + '<div class="item-separator pt-1 mb-2"></div>'
                   + '</div>'
               + '</div>'
            return lineItem;
        } // end addSavedLineItem method

        set customerId(id) {
            this.invoice.customerId = id;
        }

        set productId(id) {
            this.invoiceItem.productId = id;
        }

        set invoiceItem(item) {
            this.invoice.invoiceItems.push(item);
        }

    } // end Invoice class

    const invoice = new Invoice();








        $(document).ready(function(){
            let customerId;
            let productId;
            let invoiceItems = [];

            let customers = new Bloodhound({
              datumTokenizer: Bloodhound.tokenizers.whitespace,
              queryTokenizer: Bloodhound.tokenizers.obj.whitespace('email'),
              prefetch: '/customers/autocomplete/term',
              remote: {
                url: '/customers/autocomplete/term',
                wildcard: "term"
              }
            });

            let products = new Bloodhound({
              datumTokenizer: Bloodhound.tokenizers.whitespace,
              queryTokenizer: Bloodhound.tokenizers.obj.whitespace('name'),
              prefetch: '/products/autocomplete/term',
              remote: {
                url: '/products/autocomplete/term',
                wildcard: "term"
              }
            });

            const options = {highlight: true}

            $('.typeahead').typeahead(options, {
              minLength: 1,
              display: 'email',
              source: customers
            });

            $('#product-name-input').typeahead(options, {
              minLength: 3,
              display: 'name',
              source: products
            });

            $('.typeahead').bind('typeahead:select', function(e, customer) {
                console.log(customer);
                document.getElementById('bill-to').innerHTML =
                "<strong>" + customer.firstName + " " + customer.lastName + "</strong><br>"
                + customer.companyName + "<br>"
                + customer.email + "<br>"
                + customer.phone;

                invoice.customerId = customer.id;
                document.getElementById('customer-id-input').value = customer.id;
            });

            $('#product-name-input').bind('typeahead:select', function(e, product) {
                console.log(product);
                $('#description-input').val(product.description);
                invoice.productId = product.id;
                console.log('productId: ' + productId);

            });

            $('#customer-save-btn').on('click', function(e) {
                console.log('button clicked!');
                $('#customer-form').on('click', function(e) {
                    e.preventDefault();
                    saveCustomer()
                    $('#customer-form-modal').modal('hide');

                });
            });

            $('#add-item-row-btn').on('click', function(e) {

                invoice.createLineItemObject();

                invoice.displayLineItem();
            });

            $('#finalize-btn').on('click', function(e) {

                invoice.saveInvoice();
            });

            function saveCustomer() {

                let firstname = $('#customer-form').find( "input[name='firstName']" ).val();
                let lastname = $('#customer-form').find( "input[name='lastName']" ).val();
                let email = $('#customer-form').find( "input[name='email']" ).val();
                let phone = $('#customer-form').find( "input[name='phone']" ).val();
                let company = $('#customer-form').find( "input[name='companyName']" ).val();

                console.info($('#customer-form').find( "input[name='firstName']" ).val());

                 let jqxhr = $.ajax({
                   url: "/customers",
                   method: "post",
                   data: {
                        firstName: firstname,
                        lastName: lastname,
                        email: email,
                        phone: phone,
                        companyName: company
                      }
                   })
                   .done(function(data) {
                     console.log( "success" );
                     console.log(data);
                     invoice.customerId = data.id;
                   })
                   .fail(function() {
                     console.log( "error" );
                   })
                   .always(function() {
                        console.log("always");
                   });

                 // Perform other work here ...

                 // Set another completion function for the request above
                 jqxhr.always(function() {
                   console.log( "second complete" );
                 });
            }

        });


        </script>
    </body>
</html>
