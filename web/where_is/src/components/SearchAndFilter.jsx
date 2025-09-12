import React, { useState } from 'react';

function SearchAndFilter() {
  const [activeFilters, setActiveFilters] = useState({
    toilet: false,
    smoking: false,
    trash: false
  });

  const handleFilterClick = (filterType) => {
    setActiveFilters(prev => ({
      ...prev,
      [filterType]: !prev[filterType]
    }));
  };

  return (
    <div className="absolute top-4 left-4 right-4 bg-white rounded-lg shadow-lg p-4 z-10">
      {/* 검색 바 */}
      <div className="flex items-center space-x-3 mb-4">
        <div className="flex-1 relative">
          <div className="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
            <svg className="h-5 w-5 text-gray-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z" />
            </svg>
          </div>
          <input
            type="text"
            placeholder="목적지 또는 주소 검색"
            className="w-full bg-white pl-10 pr-4 py-3 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent"
          />
        </div>
      </div>
      
      {/* 필터 버튼들 */}
      <div className="flex items-center space-x-3">
        <button 
          onClick={() => handleFilterClick('toilet')}
          className={`px-2 py-1 rounded-md text-sm font-medium transition-colors duration-200 ${
            activeFilters.toilet 
              ? 'text-white border border-gray-300 hover:opacity-80' 
              : 'bg-white text-gray-700 border border-gray-300 hover:bg-gray-50'
          }`}
          style={activeFilters.toilet ? { backgroundColor: '#A1D6CB' } : {}}
        >
          Toilet
        </button>
        <button 
          onClick={() => handleFilterClick('smoking')}
          className={`px-2 py-1 rounded-md text-sm font-medium transition-colors duration-200 ${
            activeFilters.smoking 
              ? 'text-white border border-gray-300 hover:opacity-80' 
              : 'bg-white text-gray-700 border border-gray-300 hover:bg-gray-50'
          }`}
          style={activeFilters.smoking ? { backgroundColor: '#A19AD3' } : {}}
        >
          Smoking-Area
        </button>
        <button 
          onClick={() => handleFilterClick('trash')}
          className={`px-2 py-1 rounded-md text-sm font-medium transition-colors duration-200 ${
            activeFilters.trash 
              ? 'text-white border border-gray-300 hover:opacity-80' 
              : 'bg-white text-gray-700 border border-gray-300 hover:bg-gray-50'
          }`}
          style={activeFilters.trash ? { backgroundColor: '#FF8383' } : {}}
        >
          Trash-Can
        </button>
      </div>
    </div>
  );
}

export default SearchAndFilter;