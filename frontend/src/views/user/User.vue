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
                            <label for="title" class="float-left">UserName</label>
                            <input type="text"
                                class="form-control"
                                id="title"
                                required
                                v-model="currentUser.username"
                                name = "title"
                            />
                            </div>
                            
                            <div class="form-group">
                            <label for="title" class="float-left">Email</label>
                            <input type="text"
                                class="form-control"
                                id="title"
                                required
                                v-model="currentUser.email"
                                name = "title"
                            />
                            </div>

                            <div class="form-group">
                            <label for="title" class="float-left">Phone</label>
                            <input type="text"
                                class="form-control"
                                id="title"
                                required
                                v-model="currentUser.phone"
                                name = "title"
                            />
                            </div>

                            <div class="form-group">
                            <label for="title" class="float-left">Account Type</label>
                            <input type="text"
                                class="form-control"
                                id="title"
                                required
                                v-model="currentUser.accountType.name"
                                disabled = true;
                                name = "title"
                            />
                            </div>

                            <!-- <div class="form-group">
                            <label for="title" class="float-left">Roles</label>
                            <input type="text"
                                class="form-control"
                                id="title"
                                required
                                v-model="currentUser.roles"
                                name = "title"
                                disabled = "true"
                                hidden = "true"
                            />
                            </div> -->

                            <div class="form-group">
                            <label for="title" class="float-left">Password</label>
                            <input type="text"
                                class="form-control"
                                id="title"
                                required
                                v-model="currentUser.password"
                                name = "title"
                                disabled = "true"
                            />
                            </div>

                            <!-- <div class="form-group">
                            <label for="title" class="float-left">Roles</label>
                            <input type="text"
                                class="form-control"
                                id="title"
                                required
                                v-model="currentUser.password"
                                name = "title"
                                disabled = "true"
                                hidden = "true"
                            />
                            </div> -->

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
export default {
    name: "cast",
    data() {
        return {
            currentUser : null,
            message : ''
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
            var data = {
                username: this.currentUser.username,
                password: this.currentUser.password,
                email : this.currentUser.email,
                phone: this.currentUser.phone,
                role: this.currentUser.roles.name,
                accountType : this.currentUser.accountType.name
            };


            UserService.updateUser(this.currentUser.id , data).then(response => {
                console.log(response.data);
                this.message = 'The User was updated successfully';
                this.$router.push({name : "user-list"});
            }).catch(e => {
                console.log(e);
            });
        },
    
    },
    mounted(){
        this.message = '';
        this.getUser(this.$route.params.id);
    }
}
</script>

<style scoped>

</style>