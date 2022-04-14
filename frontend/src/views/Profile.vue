<template>
  <div class="container"> 
      <div class=" text-center mt-5 ">
        <h1>Admin Profile Page</h1>
      </div>
    <div class="row ">
        <div class="col-lg-7 mx-auto">
            <div class="card mt-2 mx-auto p-4 bg-light">
                <div class="card-body bg-light">
                    <div class="container">
                        <form id="contact-form" role="form">
                            <div class="controls">
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="form-group"> 
                                        <label for="form_name">Identification </label> 
                                        <input id="form_name" type="text" name="name" class="form-control" disabled :placeholder="[[currentUser.id]]"> 
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group"> <label for="form_lastname">Username </label> 
                                        <input id="form_lastname" type="text" name="surname" class="form-control" :placeholder="[[currentUser.username]]" disabled> </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="form-group"> 
                                        <label for="form_email">Email </label> 
                                        <input id="form_email" type="email" name="email" class="form-control" :placeholder="[[currentUser.email]]" disabled> 
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group"> 
                                        <label for="form_phone">Phone </label> 
                                        <input id="form_phone" type="number" name="phone" class="form-control" :placeholder="[[currentUser.phone]]" disabled> 
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="form-group"> <label for="form_role">Account Type </label> 
                                        <input id="form_email" type="email" name="email" class="form-control" :placeholder="[[currentUser.accountType]]" disabled> </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group"> <label for="form_phone">Authorities </label> 
                                        <!-- <li v-for="role in currentUser.roles" :key="role"> -->
                                        <input id="form_phone" type="number" name="phone" class="form-control" :placeholder="[[currentUser.roles]]" disabled>
                                        <!-- </li> -->
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-group"> <label for="form_message">String Token</label> 
                                        <textarea id="form_message" name="message" class="form-control" 
                                                :placeholder="[[currentUser.token.substring(0, 20)]]" rows="2" disabled></textarea> 
                                        </div>
                                    </div>
                                </div>
                                
                            </div>
                        </form>
                    </div>
                </div>
            </div> <!-- /.8 -->
        </div> <!-- /.row-->
    </div>
</div>
</template>

<script>
export default {
  name: 'Profile',
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    }
  },
  mounted() {
    if (!this.currentUser) {
      this.$router.push('/login');
    }
  }
};
</script>

<style scoped>
body {
    font-family: 'Lato', sans-serif
}

h1 {
    margin-bottom: 40px
}

label {
    color: #333
}

.btn-send {
    font-weight: 300;
    text-transform: uppercase;
    letter-spacing: 0.2em;
    width: 80%;
    margin-left: 3px
}

.help-block.with-errors {
    color: #ff5050;
    margin-top: 5px
}

.card {
    margin-left: 10px;
    margin-right: 10px
}
</style>