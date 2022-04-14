<template>
<div class="container">
    <div class=" text-center mt-5 ">
        <h3>Add an User</h3>
      </div>
  <div class="row ">
        <div class="col-lg-7 mx-auto">
            <div class="card mt-2 mx-auto p-4 bg-light">
                <div class="card-body bg-light">
                    <div class="container">
                        <div v-if = "!submitted">
            <div class = "form-group">
                <label for="title">Username</label>
                <input type="text"
                       class="form-control"
                       id="title"
                       required
                       v-model="user.username"
                       name="title"
                />
            </div>

            <div class = "form-group">
                <label for="title">Email</label>
                <input type="text"
                       class="form-control"
                       id="title"
                       required
                       v-model="user.email"
                       name="title"
                />
            </div>

            <div class = "form-group">
                <label for="title">Phone</label>
                <input type="text"
                       class="form-control"
                       id="title"
                       required
                       v-model="user.phone"
                       name="title"
                />
            </div>

            <div class = "form-group">
                <label for="title">Password</label>
                <input type="text"
                       class="form-control"
                       id="title"
                       required
                       v-model="user.password"
                       name="title"
                />
            </div>

            

            <button @click="saveUser" class = "btn btn-success">Submit</button>
        </div>
    <div v-else>
        <h4>You submitted successfully</h4>
        <button class="btn btn-success" @click="newUser">Add</button>
    </div>
    </div>
</div>
</div>
</div>
</div>
</div>
</template>

<script>
import axios from 'axios'
import authHeader from '../../services/auth-header'

export default {
    name : "add-user",
    data() {
        return {
            user: {
                id : null , 
                username : "",
                email: "",
                phone: "",
                password: "",
                role : null,
                accountType : null
            },
            submitted: false
        };
    },

    methods: {
        saveUser(){
            var data = {
                username : this.user.username,
                email : this.user.email,
                phone : this.user.phone,
                password : this.user.password,
                role : this.user.role,
                accountType : this.user.accountType,
            };

            axios.post('http://localhost:8080/api/auth/signup' , data , { headers: authHeader() } , {
        }).then(response => {
                this.user.id = response.data.id;
                console.log(response.data);
                this.submitted = true;
            }).catch(e => {
                console.log(e);
            });
        },

        newUser(){
            this.submitted = false;
            this.user = {};
        }
    }
};
</script>

<style>
.submit-form {
    max-width: 300px;
    margin: auto;
}
</style>
