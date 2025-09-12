import React from 'react';

function FacilityImage({ imagePath }) {
  
  return (
    <div className="w-full h-64 bg-gray-200 flex items-center justify-center overflow-hidden">
        <img 
          src={imagePath}
          alt="facility image" 
          className="w-full h-full object-cover"
        />
    </div>
  );
}

export default FacilityImage;
