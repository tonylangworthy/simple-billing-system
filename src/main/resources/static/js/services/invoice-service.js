class InvoiceService {

    constructor() {}

    lineItemCount = 0;

    invoice = {
        customerId: "",
        invoiceNote: "",
        subtotal: 0,
        invoiceTotal: 0,
        taxTotal: 0,
        invoiceItems: []
    };

    invoiceItem = {
        productId: "",
        productName: "",
        productDescription: "",
        itemQuantity: 0,
        unitPrice: 0,
        taxRate: 0,
        tax: 0
    };
    
    addInvoiceItem() {
        this.createLineItemObject();
        this.displayLineItem();
        this.calculateSubtotal();
        this.calculateSalesTax();
    }

    createLineItemObject() {

        this.invoiceItem.productName = $('#product-name-input').val()
        this.invoiceItem.productDescription = $('#description-input').val()
        this.invoiceItem.itemQuantity = $('#item-quantity-input').val()
        this.invoiceItem.unitPrice = $('#item-unit-price-input').val()

        this.invoice.invoiceItems.push(this.invoiceItem);

        console.log('Created line item object!!');
        console.log(this.invoice);

    } // end createLineItemObject method

    displayLineItem(e) {
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

        this.invoice.invoiceNote = $('#invoice-note-input').val();
        this.invoice.taxRate = $('#invoice-tax-rate').val();

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
    	console.log('appendSavedLineItem()');
    	console.log(this.invoiceItem.taxRate);
    	
       	this.lineItemCount++;

        let lineItem = '<div class="row saved-line-item">'
           + '<div class="col-md-7">'
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
            + '<div class="col-md-2">'
                + '<div class="pb-2" id="item-tax-amount-'+this.lineItemCount+'"></div>'
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

    calculateSubtotal() {

        let unitPriceCents = this.invoiceItem.unitPrice * 100;
        this.invoiceItem.unitPrice = unitPriceCents;
        
        let lineAmount = this.invoiceItem.itemQuantity * unitPriceCents; // calculate line total

        console.log("Line total: " + lineAmount);
        console.log("Line total from object: " + this.invoiceItem.unitPrice);

        console.log("Previous Subtotal in cents: " + this.invoice.subtotal);

        this.subtotalCents = lineAmount + this.invoice.subtotal; // add line total to subtotal




        this.invoice.subtotal = this.subtotalCents;
        console.log("New Subtotal in cents: " + this.subtotalCents);
        console.log("subtotal is int?: " + Number.isInteger(this.invoice.subtotal));
        this.updateSubtotalField();

        // this needs to be the last step
        this.subtotalCents = this.subtotalCent * 100;
    }

    calculateSalesTax() {
    	
    	if($('#tax-rate-input').val().trim() == '' || $('#tax-rate-input').val().trim() == 0) { return; }
    	
    	let inputTaxRate = Number.parseFloat($('#tax-rate-input').val());
    	
        console.log("Tax rate from form: " + inputTaxRate);
        console.log("typeof: " + typeof inputTaxRate);

        if(typeof inputTaxRate == 'number') {
            this.invoiceItem.taxRate = Number.parseFloat($('#tax-rate-input').val());

            let taxRate = this.invoiceItem.taxRate;
            let subtotalCents = this.invoice.subtotal;

            let taxAmount;

            let taxCalculation = {
                "taxRate": taxRate,
                "subtotal": subtotalCents
            }

            let jqxhr = $.ajax({
               url: "/calculations/tax",
               method: "post",
               contentType: "application/json",
               data: JSON.stringify(taxCalculation)
            })
            .done(function(data) {

            	let itemTaxCents = data.taxAmount * 100;
            	invoiceObj.invoiceItem.tax = itemTaxCents;
                console.log("done method Tax Amount: " + data.taxAmount);
                console.log('invoiceObj.invoiceItem.tax before addition: ' + invoiceObj.invoice.taxTotal);

                invoiceObj.invoice.taxTotal =  invoiceObj.invoice.taxTotal + itemTaxCents;

                console.log("invoiceObj.invoiceItem.tax after addtion: " + invoiceObj.invoice.taxTotal);
                

                invoiceObj.calculateTotal();

                invoiceObj.updateSalesTaxField(invoiceObj.invoiceItem.tax);
            })
            .fail(function() {
             console.log( "error" );
            })
            .always(function() {
             console.log( "complete" );
            });

            console.log("Item tax: "  + this.invoiceItem.tax);

            
    	}
    }

    calculateTotal() {
        console.log("subtotal " + this.invoice.subtotal);
        console.log("tax " + this.invoice.taxTotal);
        this.invoice.invoiceTotal = this.invoice.subtotal + this.invoice.taxTotal;
        this.updateTotalField();
    }

    updateSubtotalField() {
        let subtotal = this.invoice.subtotal / 100;
//        let subtotalDecimal = subtotal / 100;
//        console.log("subtotal decimal: " + subtotalDecimal);
        console.log("subtotal Obj: " + this.invoice.subtotal);
//        console.log("subtotalDecimal is int?: " + Number.isInteger(subtotalDecimal));
        document.getElementById("invoice-subtotal").innerHTML = '$' + subtotal.toFixed(2);
    }

    updateSalesTaxField(itemTax) {
    	let invoiceTax = this.invoice.taxTotal / 100;
    	let lineItemTax = this.invoiceItem.tax / 100;
    	document.getElementById("item-tax-amount-"+this.lineItemCount).innerHTML = lineItemTax;
        document.getElementById("invoice-tax").innerHTML = '$' + invoiceTax.toFixed(2);
    }

    updateTotalField() {
    	console.log(this.invoice.invoiceTotal);
    	let total = Number.parseInt(this.invoice.invoiceTotal);
    	console.log("Is new total int? " + Number.isInteger(total));
    	console.log("new Total: " + total);
    	total = total / 100;
        document.getElementById("invoice-total").innerHTML = '$' + total.toFixed(2);
    }

    toBankersRounding(value, precision) {
        let exponentialForm = Number(value + 'e' + precision);

        let i = Math.floor(exponentialForm), f = exponentialForm - i;
        let rounded = f === 0.5 ? ((i % 2 == 0) ? i : i + 1) : Math.round(exponentialForm);
        let finalResult = Number(rounded + 'e-' + precision).toFixed(precision);
        return finalResult;
    }

    get subtotal() {
        return this.invoice.subtotal;
    }

    get tax() {
        return this.invoice.taxTotal;
    }

    get total() {
        return this.invoice.total;
    }

    set taxRate(rate) {
        this.invoice.taxRate = rate;
    }

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

const invoiceObj = new InvoiceService();

        $(document).ready(function(){

            document.getElementById("invoice-subtotal").innerHTML = '$' + invoiceObj.invoice.subtotal.toFixed(2);
            document.getElementById("invoice-tax").innerHTML = '$' + invoiceObj.invoice.taxTotal.toFixed(2);
            document.getElementById("invoice-total").innerHTML = '$' + invoiceObj.invoice.invoiceTotal.toFixed(2);
            
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

                invoiceObj.customerId = customer.id;
                document.getElementById('customer-id-input').value = customer.id;
            });

            $('#product-name-input').bind('typeahead:select', function(e, product) {
                console.log(product);
                $('#description-input').val(product.description);
                invoiceObj.productId = product.id;
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
            	invoiceObj.addInvoiceItem();
            });

            $('#invoice-tax-rate').on('change', function() {
                invoiceObj.calculateSalesTax();

                invoiceObj.calculateTotal();
            });

            $('#finalize-btn').on('click', function(e) {

                invoiceObj.saveInvoice();
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
                     invoiceObj.customerId = data.id;
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
