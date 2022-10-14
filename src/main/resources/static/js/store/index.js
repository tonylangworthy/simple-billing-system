import actions from './actions.js';
import mutations from './mutations.js';
import state from './state.js';
import ProductStore from './store.js';

export default new ProductStore({
    actions,
    mutations,
    state
});
