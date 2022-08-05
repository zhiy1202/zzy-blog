const adminModel = {
    state :{
        navIsShow:false,
        isAdmin:false,
        isUser:false
    },
    getters:{
        navIsShow(state){
            return state.navIsShow
        }
    },
    mutations:{
        UpdateNavIsShow(state){
            state.navIsShow = !state.navIsShow
            console.log(state.navIsShow);
        }
    }
}
export default adminModel