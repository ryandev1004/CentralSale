import { Link2, Trash } from 'lucide-react';
import type { Product } from '../../types/item';

function TrackedItem({ productUrl, title, percentChange, imageUrl, currentPrice }: Product) {
    return (
        <div className="bg-gray-100 hover:bg-gray-200 rounded-lg shadow-xl w-full p-4 min-h-32 flex flex-row gap-4 relative">
                    <img 
                        src={imageUrl} 
                        alt="Men's work coat" 
                        className="w-24 h-24 object-cover rounded border"
                    />
                <h2 className="text-sm font-medium text-gray-800 line-clamp-3 flex-1">
                   {title}
                </h2>
                <div className="flex flex-col items-end gap-1">
                    <p className="text-2xl font-bold text-gray-900">${currentPrice.toFixed(2)}</p>
                    <span className={`text-xs font-semibold px-2 py-1 rounded ${
                        percentChange === 0 
                            ? 'text-gray-600 bg-gray-100' 
                            : percentChange > 0 
                                ? 'text-red-600 bg-red-100' 
                                : 'text-green-600 bg-green-100'
                    }`}>
                        {percentChange === 0 ? '--' : percentChange > 0 ? `↑ ${percentChange}%` : `↓ ${Math.abs(percentChange)}%`}
                    </span>
                    <div className="flex gap-2 mt-2">
                        <button 
                        className="text-gray-600 hover:text-blue-500 cursor-pointer"
                        onClick={() => window.open(productUrl, '_blank')}
                        >
                            <Link2 size={20}/>
                        </button>
                        <button className="text-gray-600 hover:text-red-500 cursor-pointer">
                            <Trash size={20}/>
                        </button>
                    </div>
                </div>
        </div>
    )
}

export default TrackedItem;