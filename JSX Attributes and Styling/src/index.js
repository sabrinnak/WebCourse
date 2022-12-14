import React from "react";
import ReactDOM from "react-dom";

const img = "https://picsum.photos/200";

ReactDOM.render(
  <div>
    <h1 className="heading">My Favourite Foods</h1>
    <img alt="random" src={img + "?grayscale"} />

    <img
      className="food-img"
      alt="steak"
      src="https://natashaskitchen.com/wp-content/uploads/2020/03/Pan-Seared-Steak-4.jpg"
    />
    <img
      className="food-img"
      alt="ramen"
      src="https://images.squarespace-cdn.com/content/v1/601812300e957f6685ba7026/cf59b1d8-b5c5-476d-9a0f-b56f97906930/SIMMER_Totto-Ramen_047_ABui.jpg?format=2500w"
    />
    <img
      className="food-img"
      alt="creme brulee"
      src="https://confessionsofabakingqueen.com/wp-content/uploads/2019/10/vanilla-bean-creme-brulee-recipe-1-of-1-2.jpg"
    />
  </div>,
  document.getElementById("root")
);
