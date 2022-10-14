import Component from '../lib/component.js';
import store from '../store/index.js';

export default class Subtotal extends Component {
    constructor() {
        super({
            store,
            element: document.querySelector('#subtotal-input')
        });
    }
    
    render() {
        const subTotal = Number(store.state.invoice.subtotal);
        this.element.value = subTotal.toFixed(2);
    }
}
