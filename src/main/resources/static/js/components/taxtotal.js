import Component from '../lib/component.js';
import store from '../store/index.js';

export default class TaxTotal extends Component {
    constructor() {
        super({
            store,
            element: document.querySelector('#tax-total-input')
        });
    }
    
    render() {
        const taxTotal = Number(store.state.invoice.taxTotal);
        this.element.value = taxTotal.toFixed(2);

    }
}
