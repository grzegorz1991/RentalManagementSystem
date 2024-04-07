// Define a function to add event listeners to "Add to Cart" buttons
function addEventListeners() {
    // Select all "Add to Cart" buttons
    const addToCartButtons = document.querySelectorAll('.cart-btn');

    // Add event listener to each button
    addToCartButtons.forEach(button => {
        button.addEventListener('click', addToCart);
    });
}

// Define the addToCart function to handle the click event and send a POST request
async function addToCart(event) {
    // Prevent default form submission
    event.preventDefault();

    // Get the equipment ID from the data attribute of the clicked button
    const equipmentId = event.target.dataset.equipmentId;

    // Log the equipment ID to confirm the action
    console.log(`Adding equipment with ID ${equipmentId} to cart...`);

    // Send a POST request to the backend endpoint
    try {
        const response = await fetch(`/api/cart/1/addEquipment`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ equipmentId: equipmentId, quantity: 1 })
        });

        // Check if the request was successful
        if (response.ok) {
            // Item added to cart successfully, show a success message or update the UI as needed
            console.log('Item added to cart successfully');
        } else {
            // Handle errors or display error messages
            console.error('Failed to add item to cart');
        }
    } catch (error) {
        console.error('Error:', error);
    }
}

// Define a function to initialize the script
function init() {
    addEventListeners();
}

// Call the init function when the DOM content is loaded
document.addEventListener('DOMContentLoaded', init);
