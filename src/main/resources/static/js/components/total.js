import Component from '../lib/component.js';
import store from '../store/index.js';

export default class Total extends Component {
    constructor() {
        super({
            store,
            element: document.querySelector('#invoice-total-input')
        });
    }
    
    render() {
        const total = Number(store.state.invoice.total);
        this.element.value = total.toFixed(2);
    }
}
