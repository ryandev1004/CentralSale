import { useState, useEffect } from "react";
import TrackedItem from "./TrackedItem";
import { useItems } from "../../contexts/ItemContext";
import { Loader2 } from 'lucide-react';

function Dashboard() {
    const [url, setUrl] = useState('');
    const { items, loading, fetchItems, addItem } = useItems();
    const [isAdding, setIsAdding] = useState(false);

    useEffect(() => {
        fetchItems();
    }, [fetchItems]);

    const handleAdd = async() => {
        if (!url.trim()) return;
        
        setIsAdding(true);
        try {
            await addItem(url);
            setUrl('');
        } catch (error) {
            console.error('Failed to add item:', error);
            alert('Failed to add item. Please try again.');
        } finally {
            setIsAdding(false);
        }
    };

    return (
        <div className="min-h-screen bg-gray-100 flex flex-col items-center justify-center p-4">
            <div className="flex justify-center items-center mb-4 px-4">
                <h1 className="text-2xl sm:text-4xl font-bold text-gray-800 text-center">
                    Let's track your sales!
                </h1>
            </div>
            <div className="bg-white rounded-lg shadow-lg p-4 sm:p-8 max-w-4xl w-full mx-auto">
                <div className="flex flex-col sm:flex-row gap-4 items-stretch sm:items-start mb-4">
                    <input
                        className='flex-1 p-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-yellow-400'
                        placeholder="Enter your Amazon item's URL..."
                        value={url}
                        onChange={(e) => setUrl(e.target.value)}
                        disabled={isAdding}
                    />
                    <button 
                        className="bg-yellow-400 hover:bg-yellow-500/90 rounded-xl shadow-md p-2 px-4 font-semibold cursor-pointer whitespace-nowrap disabled:opacity-50 disabled:cursor-not-allowed flex items-center justify-center"
                        onClick={handleAdd}
                        disabled={isAdding || !url.trim()}
                    >
                        {isAdding ? (
                            <Loader2 className="w-5 h-5 animate-spin" />
                        ) : 'Add Item'}
                    </button> 
                </div>
                <div className="flex flex-col gap-6">
                    {loading ? (
                        <div className="text-center py-8 text-gray-600">
                            Loading items...
                        </div>
                    ) : items.length === 0 ? (
                        <div className="text-center py-8 text-gray-500">
                            No items tracked yet. Add one to get started!
                        </div>
                    ) : (
                        <div className="flex flex-col gap-4">
                            {items.map((item) => (
                                item.product && (
                                    <TrackedItem
                                        key={item.trackerId}
                                        trackerId={item.trackerId}
                                        productUrl={item.product.productUrl}
                                        title={item.product.title}
                                        percentChange={item.product.percentChange}
                                        imageUrl={item.product.imageUrl}
                                        currentPrice={item.product.currentPrice}
                                    />
                                )
                            ))}
                        </div>
                    )}
                </div>
            </div>
        </div>
    )
}

export default Dashboard;