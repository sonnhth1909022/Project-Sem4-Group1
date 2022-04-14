<template>
  <nav class="navbar navbar-expand-lg navbar-custom ">
  <div class="collapse navbar-collapse" id="navbarNavDropdown">
    <ul class="navbar-nav ml-auto">
       <div v-if="!currentUser" class="navbar-nav ml-auto">
        <li class="nav-item">
          <router-link to="/login" class="nav-link">
                <a class="nav-link text-white" href="#" >
                    <font-awesome-icon icon="sign-in-alt" /> Login
                </a>
          </router-link>
        </li>
      </div>
        <div v-if="currentUser" class="navbar-nav ml-auto">
          <li class="nav-item">
          <a class="nav-link text-white">
            <div class="dropdown">
             <i class="fas fa-bell" style="font-size:20px">&#xf07a;</i> Notification
             <span class='badge badge-warning' id='lblCartCount'> {{sum}} </span>
            
            <div class="dropdown-content">
              <ul v-for="transaction in transactions" :key="transaction.id">
                  <li style="color:black">TK {{transaction.user.username}} : TKVIP </li>
              </ul>
            </div>
            </div>
          </a>
        </li>
        <li class="nav-item">
          <router-link to="/profile" class="nav-link text-white">
            <font-awesome-icon icon="user" />
            {{ currentUser.username }}
          </router-link>
        </li>
        <li class="nav-item">
          <a class="nav-link text-white" @click.prevent="logOut">
            <font-awesome-icon icon="sign-out-alt" /> LogOut
          </a>
        </li>
      </div>


    </ul>
  </div>
</nav>
</template>
<script>
import axios from 'axios'
import authHeader from '../../services/auth-header';
export default {
  data()
  {
    return {
            transactions: [],
            sum: ""
        };
  },

  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    },
  },
  methods: {
    logOut() {
      this.$store.dispatch('auth/logout');
      this.$router.push('/login');
    },
    getTransactions()
    {
      axios.get('http://localhost:8080/api/v1/user/admin/user/transaction02' , { headers: authHeader() }).then(response => {
                this.transactions = response.data.data;
            }).catch(e => {
                console.log(e);
            });
    },
    getSum() {
      axios.get('http://localhost:8080/api/v1/user/admin/user/totalnotdeleted' , { headers: authHeader() }).then(response => {
                this.sum = response.data.data;
            }).catch(e => {
                console.log(e);
            });
    }
  },
  mounted()
  {
    this.getTransactions();
    this.getSum();
  }
}
</script>

<style scoped>
.navbar-custom {
            background-color: #02140b;
        }
.dropdown {
  position: relative;
  display: inline-block;
}

.dropdown-content {
  display: none;
  position: absolute;
  background-color: #f9f9f9;
  min-width: 160px;
  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
  padding: 12px 16px;
  z-index: 1;
}

.dropdown:hover .dropdown-content {
  display: block;
}

.header{background:rgb(0, 178, 255);color:#fff;}
#lblCartCount {
    font-size: 12px;
    background: #ff0000;
    color: #fff;
    padding: 0 5px;
    vertical-align: top;
    margin-left: -10px;
}
.badge {
  padding-left: 9px;
  padding-right: 9px;
  -webkit-border-radius: 9px;
  -moz-border-radius: 9px;
  border-radius: 9px;
}

.label-warning[href],
.badge-warning[href] {
  background-color: #c67605;
}
</style>