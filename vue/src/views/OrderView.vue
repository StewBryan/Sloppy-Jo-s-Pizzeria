<template>
    <head>
      <title>Sloppy Joe's Menu</title>
      <meta charset="utf-8">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    </head>
  <body>
<!-- header -->
    <nav class="navbar navbar-inverse">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>                        
          </button>
          <a class="navbar-brand">
            <img class="navbar-logo" src="@/assets/spLogo.png" alt="Joe's Sloppy Pizza" />
          </a>
        </div>

        <div class="collapse navbar-collapse" id="myNavbar">
          <ul class="nav navbar-nav">
            <li class="active"><router-link v-bind:to="{name: 'home'}">Home</router-link></li>
            <li><router-link v-bind:to="{name: 'about-us'}">About Us</router-link></li>
            <li><router-link v-bind:to="{name: 'order'}">Order Now</router-link></li>
            <li><router-link v-bind:to="{name: 'contact'}">Contact Us</router-link></li>
          </ul>
          <ul class="nav navbar-nav navbar-right">
            <li><router-link v-bind:to="{name: 'shopping-cart'}"><img class="navbar-brand" src="@/assets/shopping-cart2.png" alt="shopping cart img" 
                  style="width: 65px; height: 65px;"></router-link></li>
            <li><router-link v-bind:to="{name: 'login'}">Login</router-link></li>
            <li><router-link v-bind:to="{name: 'register'}">Register</router-link></li>
          </ul>
        </div>
      </div>
    </nav>

    <div class="text-center">
      <h1>~Order Now~</h1>
      <h3>Check to See if you are in Our Delivery Area!</h3>
    </div>

    <!-- search bar       --------->
    <div class="search-container text-center">
    <input 
      type="text" 
      v-model="query" 
      @input="fetchAddresses" 
      placeholder="Search for an address"
      @focus="showAddress = true"
    />
    <ul v-if="showAddress && addresses.length">
      <li 
        v-for="(address, index) in addresses" 
        :key="index" 
        @click="checkDeliveryArea(address)"
      >
        {{ address.formatted }}
      </li>
    </ul>
    <p v-if="deliveryMessage" class="delivery-message">{{ deliveryMessage }}</p>
  </div>

  <!-- grid for photos -->
<h4>World Famous Sloppy Specialty Pizzas!  (Medium Only)</h4>
<div class="container">
  <div class="row justify-content-center">
    <div class="col-12 col-sm-6 col-md-4 col-lg-3">
      <div class="card grid-item">
        <img src="@/assets/orderSloppyPizza.png" alt="Sloppy Joe Special" class="card-img-top">
        <div class="card-body">
          <p class="card-text description"><strong>Sloppy Joe Special</strong><br><strong>$25</strong><br>Loaded with seasoned ground beef, tangy tomato sauce, and a melty cheese blend that brings the classic Sloppy Joe to your plate</p>
          <button class="btn btn-primary add-to-cart-btn" v-on:click="addSpecialtyPizzaToCart(1, 'Sloppy Joe Special', 25.00)"><strong>Add to Cart</strong></button>
        </div>
      </div>
    </div>
    <div class="col-12 col-sm-6 col-md-4 col-lg-3">
      <div class="card grid-item">
        <img src="@/assets/orderSupreme.png" alt="Supreme Pizza" class="card-img-top">
        <div class="card-body">
          <p class="card-text description"><strong>The Sloppy Supreme</strong><br><strong>$23</strong><br>Pepperoni, sausage, bell peppers, onions, and black olives, all layered over a rich tomato base and gooey mozzarella</p>
          <button class="btn btn-primary add-to-cart-btn" v-on:click="addSpecialtyPizzaToCart(2, 'The Sloppy Supreme', 23.00)"><strong>Add to Cart</strong></button>
        </div>
      </div>
    </div>
    <div class="col-12 col-sm-6 col-md-4 col-lg-3">
      <div class="card grid-item">
        <img src="@/assets/orderZestyTrio.png" alt="Zesty Trio" class="card-img-top">
        <div class="card-body">
          <p class="card-text description"><strong>Jo's Zesty Trio</strong><br><strong>$23.50</strong><br>A bold combo of spicy pepperoni, earthy mushrooms, and fiery jalapeños, all on a bed of rich tomato sauce and bubbling cheese</p>
          <button class="btn btn-primary add-to-cart-btn" v-on:click="addSpecialtyPizzaToCart(4, 'The Zesty Trio', 23.50)"><strong>Add to Cart</strong></button>
        </div>
      </div>
    </div>
    <div class="col-12 col-sm-6 col-md-4 col-lg-3">
      <div class="card grid-item">
        <img src="@/assets/orderMeatlovers.png" alt="Meatlover's Pizza" class="card-img-top">
        <div class="card-body">
          <p class="card-text description"><strong>Sloppy's Carnivore Delight</strong><br><strong>$22</strong><br>Piled high with pepperoni, sausage, ham, and bacon, all perfectly balanced by a hearty tomato sauce and melted cheese</p>
          <button class="btn btn-primary add-to-cart-btn" v-on:click="addSpecialtyPizzaToCart(3, 'Sloppy\'s Carnivore Delight', 22.00)"><strong>Add to Cart</strong></button>
        </div>
      </div>
    </div>
  </div>
</div>

<h4>Build Your Own and Make it Extra Sloppy!</h4>
<div class="container">
  <div class="row justify-content-center">
    <div class="col-12 col-sm-6 col-md-4 col-lg-3">
      <div class="card grid-item">
        <img src="@/assets/Pizzas3.png" alt="Custom Pizza" class="card-img-top">
        <div class="card-body">
          <p class="card-text description">Create Your Own Sloppy Pizza!</p>
        <router-link v-bind:to="{name: 'custom'}">
          <button class="btn btn-primary add-to-cart-btn"><strong>Build Now</strong></button>
        </router-link>
        </div>
      </div>
    </div>
    <div class="col-12 col-sm-6 col-md-4 col-lg-3">
      <div class="card grid-item">
        <img src="@/assets/pizza5.png" alt="Cooking Pizza" class="card-img-top">
        <div class="card-body">
          <p class="card-text description">All of Our Pizzas are Made to Order</p>
          <router-link v-bind:to="{name: 'custom'}">
            <button class="btn btn-primary add-to-cart-btn"><strong>Build Now</strong></button>
        </router-link>
        </div>
      </div>
    </div>
    <div class="col-12 col-sm-6 col-md-4 col-lg-3">
      <div class="card grid-item">
        <img src="@/assets/Pizzas2.png" alt="Another Pizza" class="card-img-top">
        <div class="card-body">
          <p class="card-text description">Click on the Button Below to Start Building!</p>
          <router-link v-bind:to="{name: 'custom'}">
            <button class="btn btn-primary add-to-cart-btn"><strong>Build Now</strong></button>
        </router-link>
        </div>
      </div>
    </div>
    <div class="col-12 col-sm-6 col-md-4 col-lg-3">
      <div class="card grid-item">
        <img src="@/assets/dinoPizza.png" alt="Another Pizza" class="card-img-top">
        <div class="card-body">
          <p class="card-text description">Make it Extra Sloppy!</p>
          <router-link v-bind:to="{name: 'custom'}">
            <button class="btn btn-primary add-to-cart-btn"><strong>Build Now</strong></button>
        </router-link>
        </div>
      </div>
    </div>
  </div>
</div>

<h4>Order Our Sloppy Sides!</h4>
<div class="container">
  <div class="row justify-content-center">
    <div class="col-12 col-sm-6 col-md-4 col-lg-3">
      <div class="card grid-item">
        <img src="@/assets/orderMozzSticks.png" alt="Mozzarella Sticks" class="card-img-top">
        <div class="card-body">
          <p class="card-text description"><strong>Jo's Mozz Sticks</strong><br><strong>$10</strong><br>Crispy on the outside, gooey on the inside, served with a side of marinara for dipping</p>
          <button class="btn btn-primary add-to-cart-btn" v-on:click="addSideToCart(4, 'Jo\'s Mozz Sticks', 10.00)"><strong>Add to Cart</strong></button>
        </div>
      </div>
    </div>
    <div class="col-12 col-sm-6 col-md-4 col-lg-3">
      <div class="card grid-item">
        <img src="@/assets/orderBuffaloWings.png" alt="Buffalo Wings" class="card-img-top">
        <div class="card-body">
          <p class="card-text description"><strong>Sloppy Buff Wings</strong><br><strong>$10</strong><br><strong>6</strong> Juicy chicken wings coated in a tangy, spicy Buffalo sauce, served with a cooling side of ranch or blue cheese dressing</p>
          <button class="btn btn-primary add-to-cart-btn" v-on:click="addSideToCart(5, 'Sloppy Buff Wings - 6pc', 10.00)"><strong>Add to Cart</strong></button>
        </div>
      </div>
    </div>
    <div class="col-12 col-sm-6 col-md-4 col-lg-3">
      <div class="card grid-item">
        <img src="@/assets/orderWings2.png" alt="Buffalo Wings" class="card-img-top">
        <div class="card-body">
          <p class="card-text description"><strong>Sloppy Buff Wings</strong><br><strong>$16</strong><br>Oooooweee let's make it a <strong>12 piece!</strong> Spicy Buffalo sauce, served with a cooling side of ranch or blue cheese dressing</p>
          <button class="btn btn-primary add-to-cart-btn" v-on:click="addSideToCart(6, 'Sloppy Buff Wings - 12pc', 16.00)"><strong>Add to Cart</strong></button>
        </div>
      </div>
    </div>
    <div class="col-12 col-sm-6 col-md-4 col-lg-3">
      <div class="card grid-item">
        <img src="@/assets/orderCheeseBread.png" alt="Cheesy Bread" class="card-img-top">
        <div class="card-body">
          <p class="card-text description"><strong>Cheesy Bread</strong><br><strong>$10</strong><br>Soft, cheesy perfection with a crisp, golden crust and a gooey, melted center</p>
          <button class="btn btn-primary add-to-cart-btn" v-on:click="addSideToCart(2, 'Cheesy Bread', 10.00)"><strong>Add to Cart</strong></button>
        </div>
      </div>
    </div>
    <div class="col-12 col-sm-6 col-md-4 col-lg-3">
      <div class="card grid-item">
        <img src="@/assets/orderGarlicBread.png" alt="Garlic Bread" class="card-img-top">
        <div class="card-body">
          <p class="card-text description"><strong>Garlic Bread</strong><br><strong>$7</strong><br>Warm, golden breadsticks infused with rich garlic butter and sprinkled with Parmesan cheese—perfect for dipping</p>
          <button class="btn btn-primary add-to-cart-btn" v-on:click="addSideToCart(1, 'Garlic Bread', 7.00)"><strong>Add to Cart</strong></button>
        </div>
      </div>
    </div>
    <div class="col-12 col-sm-6 col-md-4 col-lg-3">
      <div class="card grid-item">
        <img src="@/assets/orderSalad.png" alt="Salad" class="card-img-top">
        <div class="card-body">
          <p class="card-text description"><strong>Sloppy Salad</strong><br><strong>$10</strong><br>Crisp romaine lettuce, shaved Parmesan, and crunchy croutons tossed in a creamy Caesar dressing</p>
          <button class="btn btn-primary add-to-cart-btn" v-on:click="addSideToCart(3, 'Sloppy Salad', 10.00)"><strong>Add to Cart</strong></button>
        </div>
      </div>
    </div>
  </div>
</div>
  

      <footer class="container-fluid text-center">
      <p>&copy; 2024 Sloppy Jo's Pizza. All rights reserved.</p>
    </footer>
  </body>
</template>
  
  <script>
  import axios from 'axios';
  import Order from '../components/Order.vue';
  import foodService from '../services/FoodService';
 
  export default {
  data() {
    return {
      query: '',
      addresses: [],
      latitude: 41.505493,  // Latitude for 7100 Euclid Ave, Cleveland
      longitude: -81.634346, // Longitude for 7100 Euclid Ave, Cleveland
      radius: 16093.4, 
      deliveryMessage: '',
      showAddress: false,
    };
  },
  methods: {
    
    async fetchAddresses() {
      if (this.query.length > 2) {
        try {
          let response = await axios.get('https://photon.komoot.io/api/', {
            params: {
              q: this.query,
              lat: this.latitude,
              lon: this.longitude,
              limit: 5,
            },
          });

          // Filter and format addresses to include state and address number, and only include US results
          this.addresses = response.data.features
            .filter(feature => feature.properties.country === 'United States')
            .map(feature => ({
              formatted: `${feature.properties.housenumber ? feature.properties.housenumber + ' ' : ''}${feature.properties.name}, ${feature.properties.city}, ${feature.properties.state}, ${feature.properties.postcode}, ${feature.properties.country}`,
              lat: feature.geometry.coordinates[1],
              lon: feature.geometry.coordinates[0],
            }));
        } catch (error) {
          console.error('Error fetching addresses:', error);
          this.addresses = [];
        }
      } else {
        this.addresses = [];
      }
    },
    checkDeliveryArea(address) {
      const distance = this.calculateDistance(this.latitude, this.longitude, address.lat, address.lon);
      console.log(`Checking distance: ${distance} meters`); 
      if (distance <= this.radius) {
        this.deliveryMessage = `The address "${address.formatted}" is within our delivery area.`;
      } else {
        this.deliveryMessage = `The address "${address.formatted}" is not in our delivery area.`;
      }
    },
    calculateDistance(lat1, lon1, lat2, lon2) {
      const R = 6371e3; 
      const φ1 = lat1 * Math.PI / 180; 
      const φ2 = lat2 * Math.PI / 180;
      const Δφ = (lat2 - lat1) * Math.PI / 180;
      const Δλ = (lon2 - lon1) * Math.PI / 180;

      const a = Math.sin(Δφ/2) * Math.sin(Δφ/2) +
                Math.cos(φ1) * Math.cos(φ2) *
                Math.sin(Δλ/2) * Math.sin(Δλ/2);
      const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

      const distance = R * c; 
      return distance;
    },  
    handleClickOutside(event) {
      const searchContainer = this.$refs.searchContainer;
      if (searchContainer && !searchContainer.contains(event.target)) {
        this.showAddresses = false;
      }
    },
    mounted() {
    document.addEventListener('click', this.handleClickOutside);
    },
    beforeUnmount() {
      document.removeEventListener('click', this.handleClickOutside);
    },
      addSpecialtyPizzaToCart(id, name, price) {
          this.$store.commit('ADD_TO_CART', {type: 'Specialty', obj: {id: id, name: name, price: price}});
      },
      addSideToCart(id, name, price) {
          this.$store.commit('ADD_TO_CART', {type: 'Side', obj: {id: id, name: name, price: price}});
      }
  },        
};
  </script>
  
  <style scoped>

  .navbar {
      font-size: 24px;
      margin-bottom: 0;
      border-radius: 0;
      color: #e0ba20c7;
      font-family: Impact, Haettenschweiler, 'Arial Narrow Bold', sans-serif;
}


    .navbar-logo {
    max-height: 100px; /* Adjust the max height as needed */
    width: auto; /* Maintain aspect ratio */
    margin-top: -25px;
   }
      @media (max-width: 768px) {
      .navbar-logo {
        max-height: 40px; /* Adjust size for smaller screens */
      }
    }
  
  
  #page-header {
    box-shadow: 0 4px 5px 0 rgba(0,0,0,0.14), 0 1px 10px 0 rgba(0,0,0,0.12), 0 2px 4px -1px rgba(0,0,0,0.2);
    padding-left: 10px;
    padding-right: 10px;
    grid-area: header;
    display: flex;
    justify-content: space-between;
    align-items: center;
    background-color: #daa520;
  }
  
  #page-footer {
    box-shadow: 0 4px 5px 0 rgba(0,0,0,0.14), 0 1px 10px 0 rgba(0,0,0,0.12), 0 2px 4px -1px rgba(0,0,0,0.2);
    padding-left: 10px;
    padding-right: 10px;
    grid-area: footer;
    display: flex;
    justify-content: space-between;
    align-items: center;
    background-color: #e0ba20c1;
  }
  
  .resize-image {
    max-width: 200px;
    max-height: auto;
    margin-top: 20px;
  }
  
footer {
  background-color: #e0ba20c7;
  padding: 25px;
  font-size: 24px;
  font-family: Impact, Haettenschweiler, 'Arial Bold', sans-serif;
}
  
  .footer-image {
    display: grid;
    place-items: center;
  }
  
  .inline-footer-links {
    display: grid;
    grid-template-columns: repeat(3, auto); 
    column-gap: 50px; 
    justify-content: center; 
  }
  
  .nav-link {
  display: flex; 
  list-style: none; 
  padding: 0;
  margin: 0;
}

.nav-link li {
  margin-right: 50px;
  font-size: 35px;
}

.nav-link li:last-child {
  margin-right: 50px; 
}

.nav-link a {
  text-decoration: none;
  color: black;
}

.nav-link a:hover {
  color: #0000ff;
}

/* search bar /////////////////////////////////////////////////////// */
.search-container {
  width: 100%;
  max-width: 400px;
  margin: 0 auto;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
  /* background-color: #f9f9f9; */
}

.search-container input {
  width: 100%;
  padding: 8px;
  margin-bottom: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
  box-sizing: border-box;
}

.search-container ul {
  margin: 0;
  padding: 0;
  list-style-type: none;
  border-top: 1px solid #ccc;
}

.search-container li {
  padding: 8px;
  border-bottom: 1px solid #ccc;
  background-color: #fff;
  cursor: pointer;
}

.search-container li:hover {
  background-color: #e6e6e6;
}

.delivery-message {
  margin-top: 10px;
  font-weight: bold;
}

/* grid for photos /////////////////////////////////////////////////////////////////// */
.container {
  padding-bottom: 40px; 
}

.row {
  margin-bottom: 40px;
}

.grid-item {
  position: relative;
  overflow: hidden;
  transition: transform 0.3s ease;
}

.grid-item:hover {
  transform: scale(1.05);
}

.card-img-top {
  transition: transform 0.3s ease;
  height: 200px;
  object-fit: cover;
}

.grid-item:hover .card-img-top {
  transform: scale(1.1);
}

.description {
  opacity: 0;
  transition: opacity 0.3s ease;
  text-align: center;
  background-color: rgba(0, 0, 0, 0.7);
  color: white;
  padding: 10px;
  border-radius: 5px;
  position: absolute;
  bottom: 60px;
  left: 50%;
  transform: translateX(-50%);
  width: 100%;
}

.grid-item:hover .description {
  opacity: 1;
}

.add-to-cart-btn {
  position: absolute;
  bottom: 10px;
  left: 50%;
  transform: translateX(-50%);
  opacity: 0;
  transition: opacity 0.3s ease, transform 0.3s ease;
}

.grid-item:hover .add-to-cart-btn {
  opacity: 1;
  transform: translateX(-50%) scale(1.1);
}

h4 {
  font-size: 24px;
  padding-left: 5%;
  font-family: Impact, Haettenschweiler, 'Arial Narrow Bold', sans-serif;
}

h1, h3 {
  
  font-family: Impact, Haettenschweiler, 'Arial Narrow Bold', sans-serif;
}
  
  
  </style>