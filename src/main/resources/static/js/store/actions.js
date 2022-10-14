export default {
    addProduct(context, payload) {
        context.commit('addProduct', payload);
    },
    calculateSubtotal(context, payload) {
        context.commit('calculateSubtotal', payload);
    },
    calculateTotalTax(context, payload) {
        context.commit('calculateTotalTax', payload);
    },
    calculateInvoiceTotal(context, payload) {
        context.commit('calculateInvoiceTotal', payload);
    },
    clearProduct(context, payload) {
        context.commit('clearProduct', payload);
    }
};
