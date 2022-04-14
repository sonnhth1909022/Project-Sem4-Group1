<template>

<div class="container"> 
      <div class=" text-center mt-5 ">
        <h3>Update an user</h3>
      </div>
    <div class="row ">
        <div class="col-lg-7 mx-auto">
            <div class="card mt-2 mx-auto p-4 bg-light">
                <div class="card-body bg-light">
                    <div class="container">
                        <div v-if = "currentUser">
                            
                            <div class="form-group">
                            <multiselect
                                v-model="currentUser.accountType"
                                :options="optionAccountType"
                                :multiple="false"
                                :close-on-select="true"
                                placeholder="Account Types"
                                label="name"
                                track-by="id"
                            ></multiselect>
                            </div>
                            


                            <div class="form-group">                       
                            <button @click="updateUser" class="btn btn-success">Submit</button>
                            </div>
                        </div>

                        <div v-else>
                         <h4>Please choose an user</h4>
                        </div>
                </div>
            </div> 
        </div> 
    </div>
</div>
</div>
</template>

<script>
import UserService from '../../services/user/user.service'
import axios from "axios";
import authHeader from '../../services/auth-header';
import Multiselect from '@suadelabs/vue3-multiselect';
export default {
    name: "cast",
    components: { Multiselect },
    data() {
        return {
            currentUser : null,
            optionAccountType : [] ,
            message : '' ,
            account : null,
        };
    },
    methods: {
        getUser(id){
            UserService.getUserById(id).then(response => {
                this.currentUser = response.data.data;
               
            }).catch(e => {
                console.log(e);
            });
        },

        updateUser()
        {
            UserService.updateAccount(this.currentUser.id ,  this.currentUser.accountType.name ).then(response => {
               this.message = response.data.data;
               this.$router.push({name : "user-list"});
            }).catch(e => {
                console.log(e);
            });
        },
        retrieveAccountType()
        {
            axios.get('http://localhost:8080/api/v1/user/accountType' , { headers: authHeader() }).then(response => {
                this.optionAccountType = response.data.data;
            }).catch(e => {
                console.log(e);
            });
        }
    },
    mounted(){
        this.message = '';
        this.getUser(this.$route.params.id);
        this.retrieveAccountType();
    }
}
</script>

<style scoped>

</style>