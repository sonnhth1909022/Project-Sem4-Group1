import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Profile from '../views/Profile.vue'
import Revenue from '../views/Revenue.vue'
import Introduction from '../views/Introduction.vue'
// import list
import CategoryList from '../views/category/CategoryList.vue'
import CastList from '../views/cast/CastList.vue'
import DirectorList from '../views/director/DirectorList.vue'
import MovieList from '../views/movie/MovieList.vue'
import UserList from '../views/user/UserList.vue'
// import add
import AddCategory from '../views/category/AddCategory.vue'
import AddDirector from '../views/director/AddDirector.vue'
import AddCast from '../views/cast/AddCast.vue'
import AddMovie from '../views/movie/AddMovie.vue'
import AddUser from '../views/user/AddUser.vue'
// import update
import Director from '../views/director/Director.vue'
import Cast from '../views/cast/Cast.vue'
import Movie from '../views/movie/Movie.vue'
import User from '../views/user/User.vue'
import UserAccount from '../views/user/UserAccountType.vue'
import GetListMovie from '../views/movie/GetListMovie.vue'
import MovieCategory from '../views/movie/MovieCategory.vue'
import MovieCast from '../views/movie/MovieCast.vue'
import MovieDirector from '../views/movie/MovieDirector.vue'
const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/login',
    name: 'login',
    component: Login
  },
  {
    path: '/revenue',
    name: 'revenue',
    component: Revenue
  },
  {
    path: '/introduction',
    name: 'introduction',
    component: Introduction
  },
  {
    path: '/profile',
    name: 'profile',
    component: Profile
  },
  {
    path: '/category',
    name : 'category-list',
    component: CategoryList
  },
  {
    path: '/cast',
    name : 'cast-list',
    component: CastList
  },
  {
    path: '/director',
    name : 'director-list',
    component: DirectorList
  },
  {
    path: '/movie',
    name : 'movie-list',
    component: MovieList
  },
  {
    path: '/user',
    name : 'user-list',
    component: UserList
  },
  {
    path: '/addcategory',
    name : 'add-category',
    component: AddCategory
  },
  {
    path: '/adddirector',
    name : 'add-director',
    component: AddDirector
  },
  {
    path: '/adduser',
    name : 'add-user',
    component: AddUser
  },
  {
    path: '/director/:id',
    name : 'update-director',
    component: Director
  },
  {
    path: '/cast/:id',
    name : 'update-cast',
    component: Cast
  },
  {
    path: '/user/:id',
    name : 'update-user',
    component: User
  },
  {
    path: '/user/account/:id',
    name : 'update-useraccountType',
    component: UserAccount
  },
  {
    path: '/addcast',
    name : 'add-cast',
    component: AddCast
  },
  {
    path: '/addmovie',
    name : 'add-movie',
    component: AddMovie
  },
  {
    path: '/movie/:id',
    name : 'update-movie',
    component: Movie
  },
  {
    path: '/listmovie',
    name : 'get-list-movie',
    component: GetListMovie
  },
  {
    path: '/moviecast',
    name : 'movie-cast',
    component: MovieCast
  },
  {
    path: '/moviecategory',
    name : 'movie-category',
    component: MovieCategory
  },
  {
    path: '/moviedirector',
    name : 'movie-director',
    component: MovieDirector
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router;
