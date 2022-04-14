<template>
<div class="container">
        <div class=" text-center mt-5 ">
        <h1>User List</h1>
        </div>
    <div class="table-responsive">
        <div class="table-wrapper">
            <div class="table-title">
                <div class="row">
                    <div class="col-sm-8 text-left">
                        <h2>User <b>Details</b></h2>
                        <div class = "heloo">
                        <button type="button" class="btn btn-dark float-left" @click="getUserVip();">List User Vip</button>
                        <button type="button" class="btn btn-dark helo" @click="getUserNormal();">List User Normal</button>
                        </div>
                    </div>
                    
                    <div class="col-sm-4">
                   <div class="search-box">
                        <div class="input-group rounded">
                        <input type="search" class="form-control rounded" placeholder="Search" 
                        aria-label="Search" aria-describedby="search-addon"  
                        v-model="searchTitle" />
                        <span class="input-group-text border-0" id="search-addon" @click="getUserByName();">
                        <font-awesome-icon :icon = "['fas' , 'search']" />
                        </span>
                        </div>
                    </div>
                    </div>
                </div>
            </div>
            <table class="table table-striped table-hover table-bordered border-3">
                
                <thead>
                    <tr class = "bg-dark text-white">
                        <th>#</th>
                        <th>Username</th>
                        <th>Email</th>
                        <th>Phone</th>
                        <th>AccountType</th>
                        <th>Update </th>
                        <th>Change accountType</th>
                    </tr>
                </thead>
                <tbody>
                     <tr v-for="user in users" :key="user.id">
                <th scope="col">{{user.id}}</th>
                <th scope="col">{{user.username}}</th>
                <th scope="col">{{user.email}}</th>
                <th scope="col">{{user.phone}}</th>
                <th scope="col">{{user.accountType.name}}</th>
                <th scope="col"><router-link :to="'/user/' + user.id"><font-awesome-icon :icon = "['fas' , 'user-edit']" /></router-link></th>
                <th scope="col"><router-link :to="'/user/account/' + user.id"><font-awesome-icon :icon = "['fas' , 'id-badge']" /></router-link></th>
                    </tr>
                </tbody>
             </table>
             <router-link to="/adduser">
             <button type="button" class="btn btn-dark float-left">Add User</button>
        </router-link>
        </div>
    </div>  
</div>   

</template>

<script>
import UserService from '../../services/user/user.service'
import authHeader from '../../services/auth-header'
export default {
    name : "UserList",
    data() {
        return {
            users: [],
            searchTitle : "",
           
        };
    },
    methods : {
      retrieveUser()
      {
            UserService.getAllUsers().then(response => {
               this.users = response.data.data;
            }).catch(e => {
                console.log(e);
            });
      },
      refreshList(){
            this.retrieveUser();
      },
      getRequestName(searchTitle)
      {
          let params = {};
          if(searchTitle)
          {
              params["name"] = searchTitle;
              return params;
          }
      },
      getUserByName()
      {
            const params = this.getRequestName(
                this.searchTitle
            );

            UserService.getUserByName(params , { headers: authHeader() } ).then(response => {
                this.users = response.data.data;
            }).catch(e => {
                console.log(e);
            });
      },
      getUserVip()
      {
          UserService.userVip().then(response => {
                this.users = response.data.data;
            }).catch(e => {
                console.log(e);
            });
      },
      getUserNormal()
      {
          UserService.userNormal().then(response => {
                this.users = response.data.data;
            }).catch(e => {
                console.log(e);
            });
      }
    },
    mounted()
    {
        this.retrieveUser();
    },
}
</script>

<style scoped>
.heloo
{
    margin-top:10px;
}
.helo
{
    margin-left:20px;
}
body {
    color: #566787;
    background: #f5f5f5;
    font-family: 'Roboto', sans-serif;
}
.table-responsive {
    margin: 30px 0;
}
.table-wrapper {
    min-width: 1000px;
    background: #fff;
    padding: 20px;
    box-shadow: 0 1px 1px rgba(0,0,0,.05);
}
.table-title {
    padding-bottom: 10px;
    margin: 0 0 10px;
    min-width: 100%;
}
.table-title h2 {
    margin: 8px 0 0;
    font-size: 22px;
}
.search-box {
    position: relative;        
    float: right;
}
.search-box input {
    height: 34px;
    border-radius: 20px;
    border-color: #ddd;
    box-shadow: none;
}
.search-box input:focus {
    border-color: #3FBAE4;
}
.search-box i {
    color: #a0a5b1;
    position: absolute;
    font-size: 19px;
    top: 8px;
    left: 10px;
}
table.table tr th, table.table tr td {
    border-color: #e9e9e9;
}
table.table-striped tbody tr:nth-of-type(odd) {
    background-color: #fcfcfc;
}
table.table-striped.table-hover tbody tr:hover {
    background: #f5f5f5;
}
table.table th i {
    font-size: 13px;
    margin: 0 5px;
    cursor: pointer;
}
table.table td:last-child {
    width: 130px;
}
table.table td a {
    color: #a0a5b1;
    display: inline-block;
    margin: 0 5px;
}
table.table td a.view {
    color: #03A9F4;
}
table.table td a.edit {
    color: #FFC107;
}
table.table td a.delete {
    color: #E34724;
}
table.table td i {
    font-size: 19px;
}  

.border-3 {
    border-width:3px !important;
    border-color: black;
}
</style>