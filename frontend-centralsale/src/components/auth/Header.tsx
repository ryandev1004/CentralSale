import { LogOut } from 'lucide-react';
import { useAuth } from '../../contexts/AuthorizationContext';

function Header() {
    const { logout, user } = useAuth();

    const handleLogout = () => {
        logout();
    };

    return (
        <header className="w-full bg-gray-100">
            <div className="w-full mx-auto px-4 py-4 flex justify-end items-center">
                <div className="flex items-center gap-4">
                    {user && (
                        <span className="text-sm text-gray-700 hidden sm:inline">
                            {user.email}
                        </span>
                    )}
                    <button
                        onClick={handleLogout}
                        className="flex items-center gap-2 bg-red-500 hover:bg-red-900 text-white px-4 py-2 rounded-lg font-semibold transition-colors cursor-pointer"
                    >
                        <LogOut size={20} />
                        <span className="hidden sm:inline">Logout</span>
                    </button>
                </div>
            </div>
        </header>
    );
}

export default Header;