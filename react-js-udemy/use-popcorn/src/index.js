import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import { useState } from 'react';
import StarRating from './StarRating';
import App1 from './App-v1';
function Test(){
  const [movieRating, setMovieRating] = useState(0);

  return (
    <>
      <StarRating color="blue" maxRating={10} onSetRating={setMovieRating}/>
      <p>This movies was rated {movieRating} Stars</p>
    </>
  )
}
const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <App1 />
    {/* <StarRating maxRating = {5} size={20} className="className" messages = {['Terrible', 'Bad', 'Okay', 'Good', 'Amazing']} defaultRating={3}/>
    <Test/> */}
  </React.StrictMode>
);

