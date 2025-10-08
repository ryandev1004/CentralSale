import { useState } from "react";
import { useAuth } from "../../contexts/AuthorizationContext";
import { Loader2 } from 'lucide-react';

function Authorization() {
    const [form, setForm] = useState<{ email: string; password: string }>({ email: "", password: "" })
    const [newUser, setNewUser] = useState<boolean>(false);
    const [error, setError] = useState<string>("");
    const [isLoading, setIsLoading] = useState<boolean>(false);

    const { login, signup } = useAuth();

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        setError("");
        setIsLoading(true);
        try {
            if (newUser) {
                await signup(form.email, form.password);
            } else {
                await login(form.email, form.password);
            }
        } catch (error: any) {
            setError(error.response?.data?.message || "Login failed. Please try again.");
        } finally {
            setIsLoading(false);
        }
    };

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setForm({
            ...form,
            [e.target.name]: e.target.value
        });
    };

    const handleNewUser = () => {
        setNewUser(!newUser);
        setError("");
    };

    return (
        <div className="fixed top-0 left-0 bg-black/20 backdrop-blur-md w-screen h-screen flex items-center justify-center z-50">
            <div className="bg-white/85 p-8 rounded-lg shadow-xl max-w-lg w-full mx-4">
                <h2 className="text-2xl font-bold mb-6 text-center">
                    {newUser ? "Create Account" : "Welcome back!"}
                </h2>

                {error && (
                    <div className="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded mb-4">
                        {error}
                    </div>
                )}

                <form onSubmit={handleSubmit} className="space-y-2">
                    <div>
                        <label htmlFor="email" className="block font-semibold mb-2">
                            Email
                        </label>
                        <input 
                            type='email' 
                            name='email'
                            className='w-full p-2 border border-gray-300 rounded-lg mb-4 focus:outline-none focus:ring-2 focus:ring-yellow-400'
                            value={form.email}
                            onChange={handleChange}
                            required
                            disabled={isLoading}
                        />
                    </div>
                    <div>
                        <label htmlFor="password" className="block font-semibold mb-4">
                            Password
                        </label>
                        <input 
                            type='password' 
                            name='password'
                            className='w-full p-2 border border-gray-300 rounded-lg mb-4 focus:outline-none focus:ring-2 focus:ring-yellow-400'
                            value={form.password}
                            onChange={handleChange}
                            required
                            disabled={isLoading}
                        />
                    </div>
                    <button 
                        type="submit"
                        className="bg-yellow-400 w-full hover:bg-yellow-500/90 text-black font-semibold py-2 rounded-lg cursor-pointer disabled:opacity-50 disabled:cursor-not-allowed flex items-center justify-center gap-2"
                        disabled={isLoading}
                    >
                        {isLoading ? (
                            <>
                                <Loader2 className="w-5 h-5 animate-spin" />
                                <span>Loading...</span>
                            </>
                        ) : (
                            newUser ? "Create Account" : "Login"
                        )}
                    </button>
                    <p onClick={handleNewUser} className="text-sm text-center font-semibold text-gray-600 hover:text-black cursor-pointer transition-colors">
                        {newUser ? "Already have an account? Login!" : "New user? Create account here!"}
                    </p>
                </form>
            </div>
        </div>
    );
}

export default Authorization;