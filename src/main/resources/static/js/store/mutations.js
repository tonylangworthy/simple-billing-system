import store from './store.js';

export default {
    addProduct(state, payload) {
        state.invoice.items.push(payload);
        return state;
    },
    calculateSubtotal(state) {

        let lineTotals = state.invoice.items.map(lineItem => {
            let productPrice = parseFloat(lineItem.productPrice) * 100;
            let lnTotal = lineItem.productQuantity * productPrice;
            lineItem.lineTotal = lnTotal;
            return lnTotal;
        });

        let subTotal = lineTotals.reduce((previousValue, currentValue) => {
            let subtotal = previousValue + currentValue;
            return subtotal;
        }, 0); // Set the initial value to 0

        let decimalSubtotal = subTotal / 100
        state.invoice.subtotal = decimalSubtotal.toFixed(2);

        return state;
    },
    calculateTotalTax(state, payload) {
        const subtotal = state.invoice.subtotal * 100;
        const taxRate = Number(payload);
        state.invoice.taxRate = taxRate;
        const tax = (taxRate / 100) * subtotal;
        const totalTax = tax / 100;

        state.invoice.taxTotal = totalTax.toFixed(2);

        return state;
    },
    calculateInvoiceTotal(state, payload) {
        const subtotal = state.invoice.subtotal;
        const totalTax = state.invoice.taxTotal;

        if(totalTax == 0) {
            state.invoice.total = Number(subtotal).toFixed(2);
            return state;
        }

        const invoiceTotal = Number(subtotal) + Number(totalTax);

        state.invoice.total = invoiceTotal.toFixed(2);
        return state;

    },
    clearProduct(state, payload) {
        state.invoice.items.splice(payload.index, 1);
        return state;
    }
};
