; ModuleID = 'Structure.c'
source_filename = "Structure.c"
target datalayout = "E-m:m-p:32:32-i8:8:32-i16:16:32-i64:64-n32-S64"
target triple = "mips"

%struct.TEST = type { i32, i32, i32, i32 }

; Function Attrs: mustprogress nofree norecurse nosync nounwind willreturn memory(argmem: write)
define dso_local void @add(ptr noalias nocapture writeonly sret(%struct.TEST) align 4 %0, i32 inreg %1, i32 inreg %2, i32 inreg %3, i32 inreg %4) local_unnamed_addr #0 {
  store i32 %1, ptr %0, align 4, !tbaa !3
  ret void
}

; Function Attrs: noinline nounwind optnone
define dso_local i32 @main() local_unnamed_addr #1 {
  %1 = alloca i32, align 4
  %2 = alloca %struct.TEST, align 4
  %3 = alloca %struct.TEST, align 4
  %4 = alloca %struct.TEST, align 4
  store i32 0, ptr %1, align 4
  call void @llvm.lifetime.start.p0(i64 16, ptr %2) #3
  call void @llvm.lifetime.start.p0(i64 16, ptr %3) #3
  %5 = getelementptr inbounds %struct.TEST, ptr %2, i32 0, i32 0
  store i32 1, ptr %5, align 4, !tbaa !3
  %6 = getelementptr inbounds { i32, i32, i32, i32 }, ptr %2, i32 0, i32 0
  %7 = load i32, ptr %6, align 4
  %8 = getelementptr inbounds { i32, i32, i32, i32 }, ptr %2, i32 0, i32 1
  %9 = load i32, ptr %8, align 4
  %10 = getelementptr inbounds { i32, i32, i32, i32 }, ptr %2, i32 0, i32 2
  %11 = load i32, ptr %10, align 4
  %12 = getelementptr inbounds { i32, i32, i32, i32 }, ptr %2, i32 0, i32 3
  %13 = load i32, ptr %12, align 4
  call void @add(ptr sret(%struct.TEST) align 4 %4, i32 inreg %7, i32 inreg poison, i32 inreg poison, i32 inreg poison)
  %14 = getelementptr inbounds %struct.TEST, ptr %4, i32 0, i32 0
  %15 = load i32, ptr %14, align 4, !tbaa !3
  call void @llvm.lifetime.end.p0(i64 16, ptr %3) #3
  call void @llvm.lifetime.end.p0(i64 16, ptr %2) #3
  ret i32 %15
}

; Function Attrs: mustprogress nocallback nofree nosync nounwind willreturn memory(argmem: readwrite)
declare void @llvm.lifetime.start.p0(i64 immarg, ptr nocapture) #2

; Function Attrs: mustprogress nocallback nofree nosync nounwind willreturn memory(argmem: readwrite)
declare void @llvm.lifetime.end.p0(i64 immarg, ptr nocapture) #2

attributes #0 = { mustprogress nofree norecurse nosync nounwind willreturn memory(argmem: write) "frame-pointer"="all" "no-trapping-math"="true" "stack-protector-buffer-size"="8" "target-cpu"="mips32r2" "target-features"="+fpxx,+mips32r2,+nooddspreg,-noabicalls" }
attributes #1 = { noinline nounwind optnone "frame-pointer"="all" "no-trapping-math"="true" "stack-protector-buffer-size"="8" "target-cpu"="mips32r2" "target-features"="+fpxx,+mips32r2,+nooddspreg,-noabicalls" }
attributes #2 = { mustprogress nocallback nofree nosync nounwind willreturn memory(argmem: readwrite) }
attributes #3 = { nounwind }

!llvm.module.flags = !{!0, !1}
!llvm.ident = !{!2}

!0 = !{i32 1, !"wchar_size", i32 4}
!1 = !{i32 7, !"frame-pointer", i32 2}
!2 = !{!"clang version 17.0.6"}
!3 = !{!4, !5, i64 0}
!4 = !{!"TEST", !5, i64 0, !5, i64 4, !5, i64 8, !5, i64 12}
!5 = !{!"int", !6, i64 0}
!6 = !{!"omnipotent char", !7, i64 0}
!7 = !{!"Simple C/C++ TBAA"}
