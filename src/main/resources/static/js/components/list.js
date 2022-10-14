import Component from '../lib/component.js';
import store from '../store/index.js';

export default class List extends Component {
    
    constructor() {
        super({
            store,
            element: document.querySelector('.item-row')
        });
    }

    render() {
        let self = this;

        console.log('list render() called');

        if(store.state.invoice.items.length === 0) {
            self.element.innerHTML = `<p class="no-items">No products yet 😢</p>`;
            return;
        }
        
        self.element.innerHTML = `
                ${store.state.invoice.items.map(item => {
                    return `
                        <div class="row saved-line-item">
                        <div class="col-md-9">
                            <div class="pl-1">${item.productName}</div>
                        </div>
                        <div class="col-md-1"><div>${item.productQuantity}</div></div>
                        <div class="col-md-2"><div>${item.productPrice} <button class="btn btn-link" aria-label="Delete this item">❌</button></div></div>
                    </div>
                    <div class="row">
                        <div class="col-md">
                            <hr>
                        </div>
                    </div>
                    `
                }).join('')}
        `;
        
        self.element.querySelectorAll('.btn').forEach((button, index) => {
            button.addEventListener('click', () => {
                store.dispatch('clearProduct', { index });
                store.dispatch('calculateSubtotal');
                store.dispatch('calculateInvoiceTotal');
                });
        });
    }
};
