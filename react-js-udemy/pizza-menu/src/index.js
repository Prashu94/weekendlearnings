import React from "react";
import ReactDOM from "react-dom/client";
import "./index.css";

const pizzaData = [
  {
    name: "Focaccia",
    ingredients: "Bread with italian olive oil and rosemary",
    price: 6,
    photoName: "pizzas/focaccia.jpg",
    soldOut: false,
  },
  {
    name: "Pizza Margherita",
    ingredients: "Tomato and mozarella",
    price: 10,
    photoName: "pizzas/margherita.jpg",
    soldOut: false,
  },
  {
    name: "Pizza Spinaci",
    ingredients: "Tomato, mozarella, spinach, and ricotta cheese",
    price: 12,
    photoName: "pizzas/spinaci.jpg",
    soldOut: false,
  },
  {
    name: "Pizza Funghi",
    ingredients: "Tomato, mozarella, mushrooms, and onion",
    price: 12,
    photoName: "pizzas/funghi.jpg",
    soldOut: false,
  },
  {
    name: "Pizza Salamino",
    ingredients: "Tomato, mozarella, and pepperoni",
    price: 15,
    photoName: "pizzas/salamino.jpg",
    soldOut: true,
  },
  {
    name: "Pizza Prosciutto",
    ingredients: "Tomato, mozarella, ham, aragula, and burrata cheese",
    price: 18,
    photoName: "pizzas/prosciutto.jpg",
    soldOut: false,
  },
];

function App() {
  return (
    <div className="container">
      <Header />
      <Menu />
      <Footer />
    </div>
  );
}
function Header() {
  // const style = {color: 'red', fontSize: "48px", textTransform: "uppercase"};
  const style = {};
  return (
    <header className="header footer">
      <h1 style={style}>Fast React Pizza Co.</h1>
    </header>
  );
}
function Menu() {
  const pizzas = pizzaData;
  // const pizzas = [];
  return (
    <div className="menu">
      <h2>Our Menu</h2>
      {
        // pizzas.length > 0 && (
        //   <ul className="pizzas">
        //     <Pizza pizzaMenu={pizzas}/>
        //   </ul>
        pizzas.length > 0 ? (
          <>
            <p>
              Authentic Italian Cuisine. {pizzas.length} Creative Dishes to
              choose from. All from our stone oven all organic, all delicious.
            </p>
            <ul className="pizzas">
              <Pizza pizzaMenu={pizzas} />
            </ul>
          </>
        ) : (
          <p>We're Still Working on our menu. Please Come back Later. 😊</p>
        )
      }
    </div>
  );
}
function Footer() {
  const hour = new Date().getHours();
  const openHour = 8;
  const closeHour = 22;
  const isOpen = hour >= openHour && hour <= closeHour;
  console.log(isOpen);

  // if (hour >= openHour && hour <= closeHour) alert("We're currently open");
  // else alert("We're currently closed")
  // if(!isOpen){
  //   return(
  //     <div className="footer">
  //       <p>We're happy to welcome you between {openHour}:00 and {closeHour}:00.</p>
  //     </div>
  //   )
  // }
  return (
    <footer className="footer">
      {
        /* {isOpen && (
        <div className="order">
          <p>We'are Open unitl {closeHour}:00. Come Visit us or Order Online.</p>
          <button className="btn">Order</button>
        </div>
      )} */
        isOpen ? (
          <Order closeHour={closeHour} openHour={openHour} />
        ) : (
          <p>
            We're happy to welcome you between {openHour}:00 and {closeHour}:00.
          </p>
        )
      }
    </footer>
  );
}

function Order({ closeHour, openHour }) {
  return (
    <div className="order">
      <p>
        We'are Open from {openHour}:00 to {closeHour}:00. Come Visit us or Order
        Online.
      </p>
      <button className="btn">Order</button>
    </div>
  );
}

function Pizza({ pizzaMenu }) {
  return pizzaMenu.map((pizza) => {
    // if(pizza.soldOut){
    //   return null;
    // }
    return (
      <li
        className={`pizza ${pizza.soldOut ? "sold-out" : ""}`}
        key={pizza.name}
      >
        <img src={pizza.photoName} alt={pizza.name} />
        <h3>{pizza.name}</h3>
        <p>{pizza.ingredients}</p>
        {pizza.soldOut ? <span>SOLD OUT</span> : <span>{pizza.price}</span>}
        {/* <span>{pizza.soldOut ? "SOLD OUT":pizza.price}</span> */}
      </li>
    );
  });
}

const root = ReactDOM.createRoot(document.getElementById("root"));

root.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>
);
