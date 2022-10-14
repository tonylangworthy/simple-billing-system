import store from './store/index.js'; 

import List from './components/list.js';
import Subtotal from './components/subtotal.js';
import Total from './components/total.js';
import TaxTotal from './components/taxtotal.js';

const productFormElement = document.querySelector('#invoice-form');
const addRowItemElement = document.querySelector('#add-item-row-btn');
const productNameElement = document.querySelector('#product-name-input');
const productQuantityElement = document.querySelector('#product-quantity-input');
const productPriceElement = document.querySelector('#product-price-input');
const productDescriptionElement = document.querySelector('#product-description-input');
const taxRateElement = document.querySelector('#tax-rate-input');

addRowItemElement.addEventListener('click', event => {

        let productNameValue = productNameElement.value.trim();
        let productQuantityValue = productQuantityElement.value.trim();
        let productPriceValue = productPriceElement.value.trim();
        let productDescriptionValue = productDescriptionElement.value.trim();
        let taxRateValue = taxRateElement.value.trim();
        
        if(productNameValue.length && productQuantityValue.length && productPriceValue.length) {
            console.log("Calling productStore.dispatch")
    
            let lineItem = {
                productName: productNameValue,
                productQuantity: Number(productQuantityValue),
                productPrice: Number(productPriceValue).toFixed(2),
                productDescription: productDescriptionValue,
                lineTotal: 0
            }
    
            productNameElement.value = '';
            productNameElement.focus();
    
            productQuantityElement.value = '';
    
            productPriceElement.value = '';
    
            store.dispatch('addProduct', lineItem);
            productDescriptionElement.value = '';

            // Calculate the totals for each new item added
            store.dispatch('calculateSubtotal');
            store.dispatch('calculateInvoiceTotal');
            store.dispatch('calculateTotalTax', taxRateValue);
        }
    
});

taxRateElement.addEventListener('change', event => {

    const taxRateValue = taxRateElement.value.trim();

    if(taxRateValue.length) {

        const taxRate = Number(taxRateValue);

        store.dispatch('calculateTotalTax', taxRate);
        store.dispatch('calculateInvoiceTotal');

    }
});

const subtotalInstance = new Subtotal();
const productListInstance = new List();
const taxTotalInstance = new TaxTotal();
const totalInstance = new Total();

subtotalInstance.render();
productListInstance.render();
taxTotalInstance.render();
totalInstance.render();
