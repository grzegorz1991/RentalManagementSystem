// Function to open the confirmation modal
function openConfirmationModal(message, autoclose) {
    var modal = document.getElementById("myModal");
    var modalMessage = document.getElementById("modalMessage");

    // Set the message
    modalMessage.textContent = message;

    // Display the modal
    modal.style.display = "block";

    // Close the modal after autoclose milliseconds
    setTimeout(function () {
        modal.style.display = "none";
    }, autoclose);
}

// Function to open the order modal
document.addEventListener("DOMContentLoaded", function () {
    var modal = document.getElementById("order-modal");
    var modalClose = modal.querySelector(".close");

    // Function to open the modal
    function openModal() {
        modal.style.display = "block";
    }

    // Function to close the modal
    function closeModal() {
        modal.style.display = "none";
    }

    // Open the modal when the user clicks the "Add to Cart" button
    var cartButtons = document.querySelectorAll(".cart-btn");
    cartButtons.forEach(function (button) {
        button.addEventListener("click", function (event) {
            event.preventDefault();
            openModal();
            // Retrieve equipment ID from the clicked button
            var equipmentId = this.dataset.equipmentId;
            // Set the equipment ID in the modal form
            document.getElementById("order-form").setAttribute("data-equipment-id", equipmentId);
        });
    });

    // Close the modal when the close button is clicked
    modalClose.addEventListener("click", function () {
        closeModal();
    });

    // Close the modal when the user clicks outside of it
    window.addEventListener("click", function (event) {
        if (event.target == modal) {
            closeModal();
        }
    });

    // Handle form submission
    document.getElementById("order-form").addEventListener("submit", function (event) {
        // Prevent default form submission
        event.preventDefault();

        // Retrieve form data
        var startDate = document.getElementById("start-date").value;
        var endDate = document.getElementById("end-date").value;
        var quantity = document.getElementById("quantity").value;
        // Retrieve equipment ID from the button data attribute
        var equipmentId = this.dataset.equipmentId;
        // Perform further actions (e.g., send AJAX request, validate data, etc.)
        // For example, you can send an AJAX request to add the equipment to the cart
        // Replace the URL and data with your actual endpoint and data
        $.ajax({
            url: '/api/cart/1/addEquipment',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({
                equipmentId: equipmentId,
                startDate: startDate,
                endDate: endDate,
                quantity: quantity
            }),
            success: function (data) {
                console.log(data);
                closeModal();
                openConfirmationModal("Your order has been placed successfully!", 3000);
            },
            error: function (xhr, textStatus, errorThrown) {
                console.log("Error:", errorThrown);
                console.log("UPS");
                console.log(startDate);
                console.log(endDate);
                console.log(quantity);
                console.log(equipmentId);
                // Optionally, you can show an error message to the user
            }
        });
    });
});

// Function to handle closing the second modal and refreshing the page
function closeModalAndRefresh() {
    var modal = document.getElementById("myModal");
    modal.style.display = "none";
    location.reload();
}

// Close the modal when clicking on the close button or outside the modal
var closeButtons = document.getElementsByClassName("close");
for (var i = 0; i < closeButtons.length; i++) {
    closeButtons[i].onclick = function () {
        closeModalAndRefresh();
    }
}

window.onclick = function (event) {
    var modal = document.getElementById("myModal");
    if (event.target == modal) {
        closeModalAndRefresh();
    }
}