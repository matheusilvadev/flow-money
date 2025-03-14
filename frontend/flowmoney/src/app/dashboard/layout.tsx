import { DollarSign } from "lucide-react";
import React from "react";

//page structure: /dashboard
export default function DashboardLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <>
      <div className="container p-2.5 flex items-center justify-center m-auto rounded-xl shadow-xl bg-neutral-600">
        <DollarSign className="text-emerald-600" size={38} />
        <h1 className="uppercase font-bold text-slate-950">
          Flow Money - Dashboard
        </h1>
      </div>
      <div className="container p-1.5 items-center gap-4 m-auto mt-2.5 rounded-xl shadow-md bg-slate-50 ">
        {children}
      </div>
    </>
  );
}
