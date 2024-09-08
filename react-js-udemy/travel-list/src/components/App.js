import {useState} from 'react';

const initialItems = [
  { id: 1, description: "Passports", quantity: 2, packed: false },
  { id: 2, description: "Socks", quantity: 12, packed: false },
  { id: 3, description: "Charger", quantity: 1, packed: true },
];

export default function App() {
  const [items, setItems] = useState(initialItems);

  function handleDeleteItem(id){
    setItems(items => items.filter(item => item.id !== id));
  }

  function handleAddItems(item) {
    setItems(items => [...items, item]);
  }

  return (
    <div className="app">
      <Logo />
      <Form onAddItems = {handleAddItems}/>
      <PackingList items = {items} onDeleteItem = {handleDeleteItem}/>
      <Stats />
    </div>
  );
}

function Logo() {
  return <h1>Far Away 🌴</h1>;
}

function Form({onAddItems}) {
  const [description, setDescription] = useState('');
  const [quantity, setQuantity] = useState(1);
    

  function handleSubmit(e) {
    e.preventDefault();

    if(!description) return;

    const newItem = {
      id: initialItems.length + 1,
      description,
      quantity: quantity,
      packed: false,
    };

    onAddItems(newItem);

    setDescription('');
    setQuantity(1);
  }

  return (
    <form className="add-form" onSubmit={(e) => handleSubmit(e)}>
      <h3>What do you need for your 😊 trip</h3>
      <select value = {quantity} onChange={(e) => setQuantity(Number(e.target.value))}>
        {Array.from({ length: 20 }, (_, i) => i + 1).map((num) => (
          <option value={num} key={num}>
            {num}
          </option>
        ))}
      </select>
      <input type="text" placeholder="Item..." value = {description} onChange={(e) => setDescription(e.target.value)}/>
      <button>Add</button>
    </form>
  );
}
function PackingList({items, onDeleteItem}) {
  return (
    <div className="list">
      <ul>
        {items.map((item) => {
          return <Item item={item} key={item.id} onDeleteItem = {onDeleteItem}/>;
        })}
      </ul>
    </div>
  );
}

function Item({ item , onDeleteItem}) {
  return (
    <li>
      <span style={item.packed ? { textDecoration: "line-through" } : {}}>
        {item.quantity} {item.description}
      </span>
      <button onClick = {() => onDeleteItem(item.id)}>❌</button>
    </li>
  );
}
function Stats() {
  return (
    <footer className="stats">
      <em>💼 You have X items on your list, and you already packed X(X%)</em>
    </footer>
  );
}
