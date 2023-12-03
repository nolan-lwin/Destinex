import React, { useState } from 'react';
import {Link, useNavigate} from "react-router-dom";
import './wish_form_product.css';
import Slider from '@material-ui/core/Slider';

function WishFormProduct() {
    // Category Box
    const categories = {
        "Electronics": ["Mobile Phones & Accessories", "Computers & Accessories", "Cameras & Photography", "GranterHome Audio & Theater", "Video Games & Consoles", "Wearable Technology"],
        "Clothing": ["Men's Clothing", "Women's Clothing", "Kids' & Baby Clothing", "Shoes", "Jewelry", "Watches", "Handbags & Wallets"],
        "Home and Kitchen": ["Furniture", "Kitchen & Dining", "Bedding & Bath", "GranterHome Décor", "Garden & Outdoor", "GranterHome Appliances", "Storage & Organization", "Vacuums & Floor Care", "Heating, Cooling & Air Quality", "Irons & Steamers"],
        "Beauty & Personal Care" : ["Makeup", "Skincare", "Hair Care", "Fragrances", "Grooming & Shaving", "Health & Wellness"],
        "Books" : ["Literature & Fiction", "Non-Fiction", "Educational & Textbooks", "Children's Books", "Comics & Graphic Novels", "Audiobooks", "Magazines"],
        "Sports & Outdoors" : ["Exercise & Fitness", "Hunting & Fishing", "Cycling", "Team Sports", "Golf", "Leisure Sports & Game Room", "Tennis & Racquet Sports", "Outdoor Recreation", "Accessories"],
        "Toys & Games" : ["Toys for all ages", "Board Games & Puzzles", "Educational Toys", "Dolls & Action Figures", "Video Games"],
        "Baby Products" : ["Baby Gear", "Feeding", "Nursery", "Diapering", "Baby Care", "Baby Food"],
        "Pet Supplies" : ["Pet Food & Treats", "Pet Toys", "Pet Health & Wellness", "Pet Accessories"],
        "Food & Grocery" : ["Beverages", "Snacks", "Gourmet Gifts", "Fresh & Frozen Foods"],
        "Musical Instruments" : ["Guitars", "Drums & Percussion", "Keyboards", "DJ", "Recording Equipment", "Electronic Music & Karaoke"],
        "Office Products" : ["Office Electronics", "Office Furniture & Lighting", "Office Supplies", "School Supplies", "Writing Supplies", "Printers & Ink", "Office Organization", "Calculators", "Envelopes & Shipping Supplies", "Office Supplies"],
        "Industrial & Scientific" : ["Industrial Hardware", "Lab & Scientific Products", "Professional Medical Supplies", "3D Printing"],
        "Arts, Crafts & Sewing" : ["Crafting", "Art Supplies", "Sewing", "Scrapbook & Card Making Supplies", "Gift Wrapping Supplies"],
    };

    const [productName, setProductName] = useState('');
    const [date, setDate] = useState('');
    const [selectedCategory, setSelectedCategory] = useState('');
    const [selectedSubcategory, setSelectedSubcategory] = useState('');
    const [value, setValue] =  React.useState([50,200]);
    const [isFormValid, setIsFormValid] = useState(false);
    const [errorMessage, setErrorMessage] = useState('');
    const navigate = useNavigate();

    const validateForm = () => {
        if (!selectedCategory && !productName.trim() && !date) {
            setErrorMessage('Please fill in all information!');
            return false
        }
        return true;
    };

    const handleNextClick = () => {
        const isValid = validateForm();
        if (isValid) {
            navigate('/wish-recipient');
        }
    };

    const handleProductNameChange = (event) => {
        const newName = event.target.value
        setProductName(newName);
        sessionStorage.setItem("productName", newName)
    };

    const handleDateChange = (event) => {
        // console.log("wht", event.target.value)
        // console.log("hehe", typeof event.target.value)
        const newDate = event.target.value
        setDate(newDate);
        sessionStorage.setItem("date", newDate)
        // console.log(sessionStorage.getItem("date"))
    }

    const handleCategoryChange = (event) => {
        const newCategory = event.target.value
        setSelectedCategory(newCategory);
        sessionStorage.setItem("category", newCategory)
        setSelectedSubcategory(''); // Reset subcategory when category changes
    };

    const handleSubcategoryChange = (event) => {
        setSelectedSubcategory(event.target.value);
    };

    // Set the slider values and range
    const minValue = 0;
    const maxValue = 500;

    // Changing State when volume increases/decreases
    const rangeSelector = (event, newValue) => {
        setValue(newValue);
        console.log(newValue)
        // Saving product price to session storage
        sessionStorage.setItem("itemPrice", newValue)
    };

        return (
        <div className="wishFormForProduct">

            <div className="wishFormForProductTitle">
                <span className="wishFormForProductTitleText">Make a </span>
                <span className="wishFormForProductTitleWish">Wish</span>
                <p>{errorMessage && <span>{errorMessage}</span>}</p>
            </div>

            <div className='wishFormForProductContainer'>
                <div className='step1'>
                    <span className='step1Text'>Step 1 of 4</span>
                </div>

                <div className='wishFormForProductSubTitle'>
                    <span className='wishFormForProductSubTitleText'>What do you want to send?</span>
                </div>

                <div className='category'>
                    <label className='categoryText'>Category</label>
                    <select className='categoryDropdown' value={selectedCategory} onChange={handleCategoryChange}>
                        <option value="">Select Category</option>
                        {Object.keys(categories).map((category) => (
                            <option key={category} value={category}>{category}</option>
                        ))}
                    </select>
                </div>

                {selectedCategory && (
                    <div className='subcategory'>
                        <label className='subcategoryText'>Subcategory</label>
                        <select className='subcategoryDropdown' value={selectedSubcategory} onChange={handleSubcategoryChange}>
                            <option className="selectSubcategory" value="">Select Subcategory</option>
                            {categories[selectedCategory].map((subcategory) => (
                                <option key={subcategory} value={subcategory}>{subcategory}</option>
                            ))}
                        </select>
                    </div>
                )}

                <div className='productNameContainer'>
                    <div className='product'>
                        <label className='productText'>Product</label>
                        <input className='productInput' type='text' placeholder='Enter the Product Name'
                            onChange={handleProductNameChange}
                        />
                    </div>
                </div>

                <div className='budgetRange'>
                    <label className='budgetRangeText'>Budget Range</label>
                    <div className="sliderTrack">
                        <Slider
                            className = "slider"
                            value={value}
                            onChange={rangeSelector}
                            valueLabelDisplay="auto"
                            min={minValue}
                            max={maxValue}
                        />
                    </div>
                </div>

                <div className='budgetRangeDescription'>
                    <span className='budgetRangeDescriptionText'>The budget range is for products/services only. Taxes and service fees are not included.</span>
                </div>

                <div className='idealDateAndTimeContainer'>
                    <div className='idealDate'>
                        <label className='idealDateText'>Ideal Date</label>
                        <input className='idealDateInput' type='date' placeholder='Enter the Ideal Date'
                            onChange={handleDateChange}
                        />
                    </div>
                    <div className='idealTime'>
                        <label className='idealTimeText'>Ideal Time</label>
                        <input className='idealTimeInput' type='time' placeholder='Enter the Ideal Time' />
                    </div>
                </div>
            </div>

            <button className="nextButton" onClick={handleNextClick}>
                Next
            </button>
        </div>
    )
}

export default WishFormProduct;