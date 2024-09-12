import React from "react";
import "./styles.css";

const faqs = [
  {
    title: "Where are these chairs assembled?",
    text:
      "Lorem ipsum dolor sit amet consectetur, adipisicing elit. Accusantium, quaerat temporibus quas dolore provident nisi ut aliquid ratione beatae sequi aspernatur veniam repellendus."
  },
  {
    title: "How long do I have to return my chair?",
    text:
      "Pariatur recusandae dignissimos fuga voluptas unde optio nesciunt commodi beatae, explicabo natus."
  },
  {
    title: "Do you ship to countries outside the EU?",
    text:
      "Excepturi velit laborum, perspiciatis nemo perferendis reiciendis aliquam possimus dolor sed! Dolore laborum ducimus veritatis facere molestias!"
  }
];

export default function App() {
  return (
    <div>
      <Accordion faqs={faqs} />
    </div>
  );
}

function Accordion({faqs}) {
  return (
    <div className = "accordion">
      {
        faqs.map((e1, i) => {
          return <AccordianItem key = {i} data = {e1} num = {i}/>
        })
      }
    </div>
  );
}

function AccordianItem({data, num}) {
  const [isOpen, setIsOpen] = React.useState(false);
  
  function handleToggle() {
    setIsOpen((isOpen) => !isOpen);
  }

  return (
    <div className={`item ${isOpen ? "open": ""}`} onClick = {handleToggle}>
      <p className="number">{num + 1}</p>
      <p className="title">{data.title}</p>
      <p className="icon">+</p>

      {isOpen && <p className="content-box">{data.text}</p>}

    </div>
  );
}
