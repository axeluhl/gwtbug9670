# gwtbug9670
A reproduction scenario for GWT bug #9670 / #9664

Run the launch configuration "gwtbug" in an Eclipse 2019-09 with GWT plugin installed,
using GWT 2.8.2. Open the URL ``http://127.0.0.1:8888/Gwtbug.html`` in your browser.
Then, apply the following patch:

```
diff --git a/gwtbug/src/gwtbug/client/Gwtbug.java b/gwtbug/src/gwtbug/client/Gwtbug.java
index 420e7a4..821ab13 100755
--- a/gwtbug/src/gwtbug/client/Gwtbug.java
+++ b/gwtbug/src/gwtbug/client/Gwtbug.java
@@ -14,10 +14,10 @@ public class Gwtbug implements EntryPoint {
         * This is the entry point method.
         */
        public void onModuleLoad() {
+               final A2.C<String> instance2 = new A2.C<>();^M
+               final Consumer<String> methodReference2 = instance2::m;^M
                final A1.C<String> instance1 = new A1.C<>();
                final Consumer<String> methodReference1 = instance1::m;
-           final A2.C<String> instance2 = new A2.C<>();
-           final Consumer<String> methodReference2 = instance2::m;
            RootPanel.get().add(new Label("Result: "+methodReference1+", "+methodReference2));
        }
 }
```
which simply flips the order in which the two method references are constructed. Save, then refresh your browser window.
Here, it produces:
```
Gwtbug.java:18 Uncaught ReferenceError: l$e_g$ is not defined
    at svd_g$.tvd_g$ [as onModuleLoad_0_g$] (Gwtbug.java:18)
    at Array.Puc_g$ (gwtbug_00046Gwtbug__EntryMethodHolder.java:3)
    at initializeModules_0_g$ (ModuleUtils.java:44)
    at eI_g$ (Impl.java:309)
    at hI_g$ (Impl.java:368)
    at Impl.java:78
    at guc_g$ (ModuleUtils.java:55)
    at StringHashCache.java:23
```
