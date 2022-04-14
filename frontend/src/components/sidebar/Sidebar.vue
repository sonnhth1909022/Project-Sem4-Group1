<template>
    <div class="sidebar" :style="{ width: sidebarWidth }">
        <h3 class = "movie">
        <span v-if="collapsed">
          
        </span>
        <span v-else>Movie Admin</span>
        </h3>

        <br />
        <br />
        
        <SidebarLink to="/" icon="fas fa-home">DashBoard</SidebarLink>
        
        <h4 class="dropdown-header">
          <span v-if="collapsed">
            
          </span>
          <span v-else>User Management</span>
        </h4>
        <SidebarLink to="/user" icon="fas fa-user"> User </SidebarLink>
        <h4 class="dropdown-header">
          <span v-if="collapsed">
            
          </span>
          <span v-else>Movie Management</span>
        </h4>
        <SidebarLink to="/movie" icon="fa-solid fa-film">Movie</SidebarLink>
        <SidebarLink to="/cast" icon="fas fa-users">Cast</SidebarLink>
        <SidebarLink to="/director" icon="fa-solid fa-video">Director</SidebarLink>
        <SidebarLink to="/category" icon="fa fa-list-alt">Category</SidebarLink>
         <SidebarLink to="/revenue" icon="fa fa-money-bill">Revenue</SidebarLink>

        <span
            class="collapse-icon"
            :class="{ 'rotate-180': collapsed }"
            @click="toggleSidebar"
        >
        <i class="fas fa-angle-double-left" />

        
        </span>
    </div>
</template>

<script>
import SidebarLink from '../sidebar/SidebarLink.vue';
import { collapsed, toggleSidebar, sidebarWidth } from './state'
export default {
    components: { SidebarLink },
    setup() {
    return { collapsed, toggleSidebar, sidebarWidth }
    }
}
</script>

<style>
:root {
  --sidebar-bg-color: #02140b;
  --sidebar-item-hover: #38a169;
  --sidebar-item-active: #276749;
}
</style>

<style scoped>
.sidebar {
  color: white;
  background-color: var(--sidebar-bg-color);

  float: left;
  position: fixed;
  z-index: 1;
  top: 0;
  left: 0;
  bottom: 0;
  padding: 0.3em;

  transition: 0.3s ease;

  display: flex;
  flex-direction: column;
}
.movie {
  margin-top:15px;
  margin-bottom:-5px;
}
.sidebar h3 {
  height: 1.5em;
}

.collapse-icon {
  position: absolute;
  bottom: 0;
  padding: 0.2em;

  color: rgba(255, 255, 255, 0.7);

  transition: 0.2s linear;
}

.rotate-180 {
  transform: rotate(180deg);
  transition: 0.2s linear;
}
</style>