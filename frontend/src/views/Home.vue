<template>
<br />
<br />
<h2><b>DASHBOARD</b></h2>
<br />
<br />
<br />
  <!-- <div class="row">

  <div class="column">
    <div class="card">
      <router-link to="/user">
      <h3>User Management</h3>
      </router-link>
      <p>Total User</p>
      {{totalUser}}
    </div>
  </div>

  <div class="column">
    <div class="card">
      <router-link to="/movie">
      <h3>Movie Management</h3>
      </router-link>
      <p>Total Movie</p>
      {{totalMovie}}
    </div>
  </div>

</div> -->
<div class="row">

                        <!-- Earnings (Monthly) Card Example -->
                        <div class="col-xl-3 col-md-6 mb-4">
                            <div class="card border-left-primary shadow h-100 py-2">
                                <div class="card-body">
                                    <div class="row no-gutters align-items-center">
                                        <div class="col mr-2">
                                          <router-link to="/user">
                                            <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                                User Management 
                                              </div>
                                              </router-link>
                                            <div class="h5 mb-0 font-weight-bold text-gray-800">{{totalUser}}</div>
                                        </div>
                                        <div class="col-auto">
                                            <i class="fas fa-user fa-2x text-gray-300"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Earnings (Monthly) Card Example -->
                        <div class="col-xl-3 col-md-6 mb-4">
                            <div class="card border-left-success shadow h-100 py-2">
                                <div class="card-body">
                                    <div class="row no-gutters align-items-center">
                                        <div class="col mr-2">
                                          <router-link to="/movie">
                                            <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
                                                Movie Management
                                                </div>
                                          </router-link>
                                            <div class="h5 mb-0 font-weight-bold text-gray-800">{{totalMovie}}</div>
                                        </div>
                                        <div class="col-auto">
                                            <i class="fas fa-film fa-2x text-gray-300"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                         <div class="col-xl-3 col-md-6 mb-4">
                            <div class="card border-left-success shadow h-100 py-2">
                                <div class="card-body">
                                    <div class="row no-gutters align-items-center">
                                        <div class="col mr-2">
                                          <router-link to="/cast">
                                            <div class="text-xs font-weight-bold text-info text-uppercase mb-1">
                                                Cast Management
                                                </div>
                                          </router-link>
                                            <div class="h5 mb-0 font-weight-bold text-gray-800">{{totalCast}}</div>
                                        </div>
                                        <div class="col-auto">
                                            <i class="fas fa-address-book fa-2x text-gray-300"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                         <div class="col-xl-3 col-md-6 mb-4">
                            <div class="card border-left-success shadow h-100 py-2">
                                <div class="card-body">
                                    <div class="row no-gutters align-items-center">
                                        <div class="col mr-2">
                                          <router-link to="/director">
                                            <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
                                                Director Management
                                                </div>
                                          </router-link>
                                            <div class="h5 mb-0 font-weight-bold text-gray-800">{{totalDirector}}</div>
                                        </div>
                                        <div class="col-auto">
                                            <i class="fas fa-address-card fa-2x text-gray-300"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>


</div>
<br />
<br />
<br />

<div class="row">

                        <!-- Earnings (Monthly) Card Example -->
                        <div class="col-xl-3 col-md-6 mb-4">
                            <div class="card border-left-primary shadow h-100 py-2">
                                <div class="card-body">
                                    <div class="row no-gutters align-items-center">
                                        <div class="col mr-2">
                                          <router-link to="/user">
                                            <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                                New User 
                                              </div>
                                              </router-link>
                                            <div class="h5 mb-0 font-weight-bold text-gray-800">{{totalUser}}</div>
                                        </div>
                                        <div class="col-auto">
                                            <i class="fas fa-user-circle fa-2x text-gray-300"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Earnings (Monthly) Card Example -->
                        <div class="col-xl-3 col-md-6 mb-4">
                            <div class="card border-left-success shadow h-100 py-2">
                                <div class="card-body">
                                    <div class="row no-gutters align-items-center">
                                        <div class="col mr-2">
                                          <router-link to="/category">
                                            <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
                                                Category Management
                                                </div>
                                          </router-link>
                                            <div class="h5 mb-0 font-weight-bold text-gray-800">{{totalCategory}}</div>
                                        </div>
                                        <div class="col-auto">
                                            <i class="fas fa-monument fa-2x text-gray-300"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                         <div class="col-xl-3 col-md-6 mb-4">
                            <div class="card border-left-success shadow h-100 py-2">
                                <div class="card-body">
                                    <div class="row no-gutters align-items-center">
                                        <div class="col mr-2">
                                          <router-link to="/revenue">
                                            <div class="text-xs font-weight-bold text-info text-uppercase mb-1">
                                                Revenue Management
                                                </div>
                                          </router-link>
                                            <div class="h5 mb-0 font-weight-bold text-gray-800">{{totalRevenue}}</div>
                                        </div>
                                        <div class="col-auto">
                                            <i class="fas fa-money-bill-wave fa-2x text-gray-300"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                         <div class="col-xl-3 col-md-6 mb-4">
                            <div class="card border-left-success shadow h-100 py-2">
                                <div class="card-body">
                                    <div class="row no-gutters align-items-center">
                                        <div class="col mr-2">
                                           <router-link to="/user">
                                            <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
                                                User Vip
                                                </div>
                                           </router-link>
                                            <div class="h5 mb-0 font-weight-bold text-gray-800">{{totalUserVip}}</div>
                                        </div>
                                        <div class="col-auto">
                                            <i class="fas fa-user-plus fa-2x text-gray-300"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>


</div>
</template>

<script>
import axios from 'axios'
import authHeader from '../services/auth-header';
export default {
  name: 'Home',
  data() {
        return {
            totalUser : "",
            totalMovie : "",
            totalDirector: "",
            totalCast:"",
            totalCategory:"",
            totalRevenue:"",
            totalUserVip:""
        };
    },
  methods :
  {
     getTotalUser()
     {
        axios.get('http://localhost:8080/api/v1/user/userCount' ,  { headers: authHeader() }).then(response => {
                this.totalUser = response.data.data;
            }).catch(e => {
                console.log(e);
            });
     },
     getTotalMovie()
     {
        axios.get('http://localhost:8080/api/v1/movie/movieCount' , { headers: authHeader() }).then(response => {
                this.totalMovie = response.data.data;
            }).catch(e => {
                console.log(e);
            });
     },
     getTotalDirector()
     {
        axios.get('http://localhost:8080/api/v1/director/directorCount' , { headers: authHeader() }).then(response => {
                this.totalDirector = response.data.data;
            }).catch(e => {
                console.log(e);
            });
     },
     getTotalCast()
     {
        axios.get('http://localhost:8080/api/v1/cast/castCount' , { headers: authHeader() }).then(response => {
                this.totalCast = response.data.data;
            }).catch(e => {
                console.log(e);
            });
     },
     getTotalCategory()
     {
        axios.get('http://localhost:8080/api/v1/category/categoryCount' , { headers: authHeader() }).then(response => {
                this.totalCategory = response.data.data;
            }).catch(e => {
                console.log(e);
            });
     },
     getTotalRevenue()
     {
        axios.get('http://localhost:8080/api/v1/user/admin/user/sum' , { headers: authHeader() }).then(response => {
                this.totalRevenue = response.data.data;
            }).catch(e => {
                console.log(e);
            });
     },
     getTotalUserVip()
     {
        axios.get('http://localhost:8080/api/v1/user/userVipCount' , { headers: authHeader() }).then(response => {
                this.totalUserVip = response.data.data;
            }).catch(e => {
                console.log(e);
            });
     },
  },
  mounted()
  {
      this.getTotalUser(),
      this.getTotalMovie() ,
      this.getTotalDirector(),
      this.getTotalCast(),
      this.getTotalCategory(),
      this.getTotalRevenue(),
      this.getTotalUserVip()
  },
}
</script>

<style scoped>
* {
  box-sizing: border-box;
}

body {
  font-family: Arial, Helvetica, sans-serif;
}

/* Float four columns side by side */
.column {
  float: left;
  width: 33%;
  padding: 0 10px;
}

/* Remove extra left and right margins, due to padding */
.row {margin: 0 -5px;}

/* Clear floats after the columns */
.row:after {
  content: "";
  display: table;
  clear: both;
}

/* Responsive columns */
@media screen and (max-width: 600px) {
  .column {
    width: 100%;
    display: block;
    margin-bottom: 20px;
  }
}

/* Style the counter cards */
.card {
  box-shadow: 0 4px 8px 0 rgba(179, 169, 169, 0.2);
  padding: 16px;
  text-align: center;

  background-color: #e8ecec;
}

</style>
