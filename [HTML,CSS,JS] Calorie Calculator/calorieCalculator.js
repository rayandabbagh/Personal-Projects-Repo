function calculate() {
	let gender = document.getElementById("gender").value;
	let age = document.getElementById("age").value;
	let height = document.getElementById("height").value;
	let weight = document.getElementById("weight").value;
	let activity = document.getElementById("activity").value;
	let caloriesInput = document.getElementById("caloriesInput").value;
	
	if (gender === "male") {
	  let bmr = 88.36 + (13.4 * weight) + (4.8 * height) - (5.7 * age);
	  let calorieIntake = bmr * activity;
	  let weightLossCalorieIntake = calorieIntake - 500;
	  let remainingCalories = calorieIntake - caloriesInput;
	  
	  document.getElementById("currentCalorieIntake").value = Math.round(calorieIntake);
	  document.getElementById("weightLossCalorieIntake").value = Math.round(weightLossCalorieIntake);
	  document.getElementById("caloriesCount").value = remainingCalories;
	} else if (gender === "female") {
	  let bmr = 447.6 + (9.2 * weight) + (3.1 * height) - (4.3 * age);
	  let calorieIntake = bmr * activity;
	  let weightLossCalorieIntake = calorieIntake - 500;
	  let remainingCalories = calorieIntake - caloriesInput;
	  
	  document.getElementById("currentCalorieIntake").value = Math.round(calorieIntake);
	  document.getElementById("weightLossCalorieIntake").value = Math.round(weightLossCalorieIntake);
	  document.getElementById("caloriesCount").value = remainingCalories;
	}
  }
  